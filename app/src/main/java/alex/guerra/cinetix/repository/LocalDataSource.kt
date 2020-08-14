package alex.guerra.cinetix.repository

import alex.guerra.cinetix.CinetixApp
import alex.guerra.cinetix.domain.MovieEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LocalDataSource(application: CinetixApp) : MovieLocalDataSource {
    private val database = application.db.movieDao()
    override suspend fun getAll() = withContext(Dispatchers.IO) {
        database.getAll()
    }

    override suspend fun findById(id: Int) = withContext(Dispatchers.IO) {
        database.findById(id)
    }

    override suspend fun movieCount() = withContext(Dispatchers.IO) {
        database.movieCount()
    }

    override suspend fun insertMovies(movies: List<MovieEntity>) = withContext(Dispatchers.IO) {
        database.insertMovies(movies)
    }

    override suspend fun updateMovie(movie: MovieEntity) = withContext(Dispatchers.IO) {
        database.updateMovie(movie)
    }
}
