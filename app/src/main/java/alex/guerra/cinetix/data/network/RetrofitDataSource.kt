package alex.guerra.cinetix.data.network

import alex.guerra.cinetix.data.toDomainMovie
import alex.guerra.data.source.RemoteDataSource

class RetrofitDataSource : RemoteDataSource {
    private val service: TheMovieDbService = TmdbClient.tmdbService
    override suspend fun getPopularMovies(region: String) =
        service.listPopularMoviesAsync(region).results.map(MovieServer::toDomainMovie)
}
