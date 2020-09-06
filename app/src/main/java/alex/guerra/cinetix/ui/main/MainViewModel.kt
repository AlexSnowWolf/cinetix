package alex.guerra.cinetix.ui.main

import alex.guerra.cinetix.ui.common.Event
import alex.guerra.domain.Movie
import alex.guerra.usecases.GetPopularMovies
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel(private val getPopularMovies: GetPopularMovies) : ViewModel() {

    sealed class UiModel {
        object Loading : UiModel()
        class Content(val movies: List<Movie>) : UiModel()
        object RequestLocationPermission : UiModel()
    }

    private val _model = MutableLiveData<UiModel>()
    val model: LiveData<UiModel>
        get() {
            if (_model.value == null) refresh()
            return _model
        }

    private val _navigation = MutableLiveData<Event<Movie>>()
    val navigation: LiveData<Event<Movie>> get() = _navigation

    private fun refresh() {
        _model.value =
            UiModel.RequestLocationPermission
    }

    fun getRemotePopularMovies() {
        viewModelScope.launch {
            _model.value =
                UiModel.Loading
            _model.value =
                UiModel.Content(
                    getPopularMovies()
                )
        }
    }

    fun onMovieClicked(movie: Movie) {
        _navigation.value = Event(movie)
    }
}
