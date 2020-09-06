package alex.guerra.usecases

import alex.guerra.data.repository.MoviesRepository

class GetPopularMovies(private val moviesRepository: MoviesRepository) {
    suspend operator fun invoke() = moviesRepository.getPopularMovies()
}
