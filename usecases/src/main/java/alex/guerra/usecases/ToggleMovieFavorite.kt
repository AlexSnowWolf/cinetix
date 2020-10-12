package alex.guerra.usecases

import alex.guerra.data.repository.MoviesRepository
import alex.guerra.domain.Movie

class ToggleMovieFavorite(private val repository: MoviesRepository) {
    suspend operator fun invoke(movie: Movie): Movie = with(movie) {
        copy(favorite = !favorite).also { repository.update(it) }
    }
}
