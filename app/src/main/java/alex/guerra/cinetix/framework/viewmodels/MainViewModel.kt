package alex.guerra.cinetix.framework.viewmodels

import alex.guerra.cinetix.domain.MovieRemote
import alex.guerra.cinetix.framework.Event
import alex.guerra.cinetix.framework.UseCase
import alex.guerra.cinetix.repository.MovieRepository
import alex.guerra.cinetix.repository.RegionRepository
import alex.guerra.cinetix.usecase.GetPopularMovies
import alex.guerra.cinetix.usecase.GetRegion
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel(
    movieRepository: MovieRepository,
    regionRepository: RegionRepository
) : ViewModel() {

    private val useCase = UseCase(
        getPopularMovies = GetPopularMovies(movieRepository),
        getRegion = GetRegion(regionRepository)
    )

    sealed class UiModel {
        object Loading : UiModel()
        class Content(val movies: List<MovieRemote>) : UiModel()
        object RequestLocationPermission : UiModel()
    }

    private val _model = MutableLiveData<UiModel>()
    val model: LiveData<UiModel>
        get() {
            if (_model.value == null) refresh()
            return _model
        }

    private val _navigation = MutableLiveData<Event<MovieRemote>>()
    val navigation: LiveData<Event<MovieRemote>> get() = _navigation

    private fun refresh() {
        _model.value = UiModel.RequestLocationPermission
    }

    fun getRemotePopularMovies() {
        viewModelScope.launch {
            _model.value = UiModel.Loading
            val regionResult = useCase.getRegion()
            _model.value = UiModel.Content(useCase.getPopularMovies(regionResult).results)
        }
    }

    fun onMovieClicked(movie: MovieRemote) {
        _navigation.value = Event(movie)
    }
}
