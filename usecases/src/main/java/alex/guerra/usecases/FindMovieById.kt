package alex.guerra.usecases

import alex.guerra.data.repository.MoviesRepository

class FindMovieById(private val repository: MoviesRepository) {
    suspend operator fun invoke(id: Int) = repository.findById(id)
}
