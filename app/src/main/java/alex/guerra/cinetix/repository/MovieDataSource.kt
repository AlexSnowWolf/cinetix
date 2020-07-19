package alex.guerra.cinetix.repository

import alex.guerra.cinetix.domain.MoviesRemoteResult

interface MovieDataSource {
    suspend fun getPopularMoviesByRegion(region: String) : MoviesRemoteResult
}