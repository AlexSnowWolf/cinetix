package alex.guerra.cinetix.usecase

import alex.guerra.cinetix.repository.MovieRepository

class GetPopularMovies(private val repository: MovieRepository) {
    suspend operator fun invoke(region: String) = repository.getRemotePopularByRegion(region)
}