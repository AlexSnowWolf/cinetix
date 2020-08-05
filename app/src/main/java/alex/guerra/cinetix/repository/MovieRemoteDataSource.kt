package alex.guerra.cinetix.repository

import alex.guerra.cinetix.domain.MoviesRemoteResult

interface MovieRemoteDataSource {
    suspend fun getPopularMoviesByRegion(region: String): MoviesRemoteResult
}
