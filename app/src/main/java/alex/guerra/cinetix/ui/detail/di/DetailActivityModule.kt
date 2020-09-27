package alex.guerra.cinetix.ui.detail.di

import alex.guerra.cinetix.ui.detail.DetailViewModel
import alex.guerra.data.repository.MoviesRepository
import alex.guerra.usecases.FindMovieById
import alex.guerra.usecases.ToggleMovieFavorite
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher

@Module
class DetailActivityModule(private val movieId: Int) {

    @Provides
    fun findMovieByIdProvider(moviesRepository: MoviesRepository) = FindMovieById(moviesRepository)

    @Provides
    fun toggleMovieFavoriteProvider(moviesRepository: MoviesRepository) =
        ToggleMovieFavorite(moviesRepository)

    @Provides
    fun detailViewModelProvider(
        findMovieById: FindMovieById,
        toggleMovieFavorite: ToggleMovieFavorite,
        uiDispatcher: CoroutineDispatcher
    ): DetailViewModel {
        return DetailViewModel(movieId, findMovieById, toggleMovieFavorite, uiDispatcher)
    }
}
