package alex.guerra.cinetix.repository

class MovieRepository(private val remote: RemoteDataSource) {
    suspend fun getRemotePopularByRegion(region: String) = remote.getPopularMoviesByRegion(region)
}
