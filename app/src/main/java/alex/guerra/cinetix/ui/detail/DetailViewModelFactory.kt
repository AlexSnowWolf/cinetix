package alex.guerra.cinetix.ui.detail

import alex.guerra.cinetix.CinetixApp
import alex.guerra.cinetix.data.AndroidPermissionChecker
import alex.guerra.cinetix.data.PlayServicesLocationDataSource
import alex.guerra.cinetix.data.database.MovieDatabase
import alex.guerra.cinetix.data.database.RoomDataSource
import alex.guerra.cinetix.data.network.RetrofitDataSource
import alex.guerra.data.repository.MoviesRepository
import alex.guerra.usecases.FindMovieById
import alex.guerra.usecases.ToggleMovieFavorite
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room

class DetailViewModelFactory(
    private val movieId: Int,
    private val application: CinetixApp
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val playServicesLocationDataSource =
            PlayServicesLocationDataSource(
                application
            )
        val checker = AndroidPermissionChecker(application)
        val regionRepository =
            alex.guerra.data.repository.RegionRepository(
                playServicesLocationDataSource,
                checker
            )
        val localDataSource =
            RoomDataSource(Room.databaseBuilder(application, MovieDatabase::class.java, "MovieDb").build())
        val movieRepository =
            MoviesRepository(
                localDataSource,
                RetrofitDataSource(),
                regionRepository
            )

        val findMovieById = FindMovieById(movieRepository)
        val toggleMovieFavorite = ToggleMovieFavorite(movieRepository)

        return modelClass.getConstructor(
            Int::class.java,
            FindMovieById::class.java,
            ToggleMovieFavorite::class.java
        ).newInstance(movieId, findMovieById, toggleMovieFavorite)

        throw IllegalArgumentException("wrong dependencies")
    }
}
