package alex.guerra.cinetix.repository

import alex.guerra.cinetix.domain.MovieEntity

interface MovieLocalDataSource {

    suspend fun getAll(): List<MovieEntity>

    suspend fun findById(id: Int): MovieEntity

    suspend fun movieCount(): Int

    suspend fun insertMovies(movies: List<MovieEntity>)

    suspend fun updateMovie(movie: MovieEntity)
}
