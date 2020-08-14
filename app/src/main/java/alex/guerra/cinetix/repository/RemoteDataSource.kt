package alex.guerra.cinetix.repository

import alex.guerra.cinetix.domain.MovieEntity
import alex.guerra.cinetix.domain.MoviesRemoteResult
import alex.guerra.cinetix.framework.networking.TheMovieDbService
import alex.guerra.cinetix.framework.networking.TmdbClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RemoteDataSource : MovieRemoteDataSource {
    private val service: TheMovieDbService = TmdbClient.tmdbService

    override suspend fun getPopularMoviesByRegion(region: String): MoviesRemoteResult =
        withContext(Dispatchers.IO) { service.listPopularMoviesAsync(region)}
}
