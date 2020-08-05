package alex.guerra.cinetix.framework.networking

import alex.guerra.cinetix.domain.MoviesRemoteResult
import retrofit2.http.GET
import retrofit2.http.Query

interface TheMovieDbService {
    @GET("discover/movie?sort_by=popularity.desc")
    suspend fun listPopularMoviesAsync(
        @Query("region") region: String
    ): MoviesRemoteResult
}
