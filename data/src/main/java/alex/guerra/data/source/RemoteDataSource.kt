package alex.guerra.data.source

import alex.guerra.domain.Movie

interface RemoteDataSource {
    suspend fun getPopularMovies(region: String): List<Movie>
}
