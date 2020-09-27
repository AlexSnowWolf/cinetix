package alex.guerra.cinetix.ui.detail

import alex.guerra.cinetix.ui.common.ScopeViewModel
import alex.guerra.domain.Movie
import alex.guerra.usecases.FindMovieById
import alex.guerra.usecases.ToggleMovieFavorite
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

class DetailViewModel(
    private val movieId: Int,
    private val findMovieById: FindMovieById,
    private val toggleMovieFavorite: ToggleMovieFavorite,
    uiDispatcher: CoroutineDispatcher

) : ScopeViewModel(uiDispatcher) {

    data class UiModel(val movie: Movie)

    private val _model = MutableLiveData<UiModel>()
    val model: LiveData<UiModel>
        get() {
            if (_model.value == null) findMovie()
            return _model
        }

    private fun findMovie() = launch {
        println("DB busqueda")
        _model.value = UiModel(findMovieById(movieId))
    }

    fun onFavoriteClicked() {
        launch {
            _model.value?.movie?.let {
                _model.value = UiModel(toggleMovieFavorite(it))
            }
        }
    }
}
