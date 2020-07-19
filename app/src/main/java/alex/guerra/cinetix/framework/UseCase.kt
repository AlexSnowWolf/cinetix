package alex.guerra.cinetix.framework

import alex.guerra.cinetix.usecase.GetPopularMovies

data class UseCase (
    val getPopularMovies: GetPopularMovies
)