package alex.guerra.cinetix.ui.main.di

import alex.guerra.cinetix.ui.main.MainViewModel
import alex.guerra.data.repository.MoviesRepository
import alex.guerra.usecases.GetPopularMovies
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher

@Module
class MainActivityModule {

    @Provides
    fun getPopularMoviesProvider(moviesRepository: MoviesRepository) =
        GetPopularMovies(moviesRepository)

    @Provides
    fun mainViewModelProvider(
        getPopularMovies: GetPopularMovies,
        uiDispatcher: CoroutineDispatcher
    ) = MainViewModel(getPopularMovies, uiDispatcher)
}
