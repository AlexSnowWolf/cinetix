package alex.guerra.cinetix.data.database

import alex.guerra.cinetix.data.toDomainMovie
import alex.guerra.cinetix.data.toRoomMovie
import alex.guerra.data.source.LocalDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import alex.guerra.domain.Movie as DomainMovie

class RoomDataSource(db: MovieDatabase) : LocalDataSource {

    private val movieDao = db.movieDao()

    override suspend fun isEmpty() = withContext(Dispatchers.IO) { movieDao.movieCount() <= 0 }

    override suspend fun saveMovies(movies: List<DomainMovie>) = withContext(Dispatchers.IO) {
        movieDao.insertMovies(movies.map(DomainMovie::toRoomMovie))
    }

    override suspend fun getPopularMovies() = withContext(Dispatchers.IO) {
        movieDao.getAll().map(MovieEntity::toDomainMovie)
    }

    override suspend fun findById(id: Int) =
        withContext(Dispatchers.IO) { movieDao.findById(id).toDomainMovie() }

    override suspend fun update(movie: DomainMovie) = withContext(Dispatchers.IO) {
        movieDao.updateMovie(movie.toRoomMovie())
    }
}
