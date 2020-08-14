package alex.guerra.cinetix.domain

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MovieEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val overview: String,
    val releaseDate: String,
    val posterPath: String,
    val backdropPath: String = "",
    val originalLanguage: String,
    val originalTitle: String,
    val popularity: Double,
    val voteAverage: Double,
    val favorite: Boolean
) {
    companion object {
        fun fromServerMovie(movie: MovieServer) =
            MovieEntity(
                id = 0,
                backdropPath = movie.backdropPath ?: "",
                originalLanguage = movie.originalLanguage,
                originalTitle = movie.originalTitle,
                overview = movie.overview,
                popularity = movie.popularity,
                posterPath = movie.posterPath,
                releaseDate = movie.releaseDate,
                title = movie.title,
                voteAverage = movie.voteAverage,
                favorite = false
            )
    }
}
