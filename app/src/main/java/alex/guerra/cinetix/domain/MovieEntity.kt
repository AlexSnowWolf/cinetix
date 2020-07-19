package alex.guerra.cinetix.domain

import com.google.gson.annotations.SerializedName

data class MovieEntity(
    val id: Long = 0L,
    val adult: Boolean,
    val backdropPath: String = "",
    val genreIds: List<Int>,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val releaseDate: String,
    val title: String,
    val video: Boolean,
    val voteAverage: Double,
    val voteCount: Int
) {
    companion object {
        fun fromMovieRemote(movie: MovieRemote) =
            MovieEntity(
                id = movie.id.toLong(),
                adult = movie.adult,
                backdropPath = movie.backdropPath ?: "",
                genreIds = movie.genreIds,
                originalLanguage = movie.originalLanguage,
                originalTitle = movie.originalTitle,
                overview = movie.overview,
                popularity = movie.popularity,
                posterPath = movie.posterPath,
                releaseDate = movie.releaseDate,
                title = movie.title,
                video = movie.video,
                voteAverage = movie.voteAverage,
                voteCount = movie.voteCount
            )
    }

    fun toMovieRemote() = MovieRemote(
        id = id.toInt(),
        adult = adult,
        backdropPath = backdropPath,
        genreIds = genreIds,
        originalLanguage = originalLanguage,
        originalTitle = originalTitle,
        overview = overview,
        popularity = popularity,
        posterPath = posterPath,
        releaseDate = releaseDate,
        title = title,
        video = video,
        voteAverage = voteAverage,
        voteCount = voteCount
    )
}