package alex.guerra.cinetix.framework.viewmodels

import alex.guerra.cinetix.domain.MovieEntity
import alex.guerra.cinetix.domain.MovieServer
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

class MainViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    sealed class UiModel {
        object Loading : UiModel()
        class Content(val movies: List<MovieEntity>) : UiModel()
        object RequestLocationPermission : UiModel()
    }

    private val _model = MutableLiveData<UiModel>()
    val model: LiveData<UiModel>
        get() {
            if (_model.value == null) refresh()
            return _model
        }

    private val _navigation = MutableLiveData<Event<MovieEntity>>()
    val navigation: LiveData<Event<MovieEntity>> get() = _navigation

    private fun refresh() {
        _model.value = UiModel.RequestLocationPermission
    }

    fun getRemotePopularMovies() {
        viewModelScope.launch {
            _model.value = UiModel.Loading
            _model.value = UiModel.Content(movieRepository.findPopularMovies())
        }
    }

    fun onMovieClicked(movie: MovieEntity) {
        _navigation.value = Event(movie)
    }
}
