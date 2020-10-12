package alex.guerra.cinetix.data

import alex.guerra.cinetix.data.database.MovieEntity
import alex.guerra.cinetix.data.network.MovieServer
import alex.guerra.domain.Movie

fun Movie.toRoomMovie(): MovieEntity = MovieEntity(
    id,
    title,
    overview,
    releaseDate,
    posterPath,
    backdropPath,
    originalLanguage,
    originalTitle,
    popularity,
    voteAverage,
    favorite
)

fun MovieEntity.toDomainMovie(): Movie = Movie(
    id,
    title,
    overview,
    releaseDate,
    posterPath,
    backdropPath,
    originalLanguage,
    originalTitle,
    popularity,
    voteAverage,
    favorite
)

fun MovieServer.toDomainMovie(): Movie = Movie(
    id = 0,
    backdropPath = backdropPath ?: "",
    originalLanguage = originalLanguage,
    originalTitle = originalTitle,
    overview = overview,
    popularity = popularity,
    posterPath = posterPath,
    releaseDate = releaseDate,
    title = title,
    voteAverage = voteAverage,
    favorite = false
)
