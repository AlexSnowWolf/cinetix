package alex.guerra.cinetix.framework

import alex.guerra.cinetix.usecase.GetPopularMovies
import alex.guerra.cinetix.usecase.GetRegion

data class UseCase(
    val getPopularMovies: GetPopularMovies,
    val getRegion: GetRegion
)
