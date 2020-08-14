package alex.guerra.cinetix.repository

import alex.guerra.cinetix.CinetixApp
import alex.guerra.cinetix.domain.MovieEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MovieRepository(
    private val remote: RemoteDataSource,
    private val local: LocalDataSource,
    private val region: RegionRepository
) {

    suspend fun getRemotePopularByRegion(region: String) = remote.getPopularMoviesByRegion(region)

    suspend fun findPopularMovies(): List<MovieEntity> = withContext(Dispatchers.IO) {
        with(local) {
            if (movieCount() <= 0) {
                val regionString = region.findLastRegion()
                val movies = remote.getPopularMoviesByRegion(regionString)
                    .results.map { MovieEntity.fromServerMovie(it) }
                insertMovies(movies)
            }
            getAll()
        }
    }
}
