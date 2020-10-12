package alex.guerra.cinetix.ui.main

import alex.guerra.cinetix.CinetixApp
import alex.guerra.cinetix.data.AndroidPermissionChecker
import alex.guerra.cinetix.data.PlayServicesLocationDataSource
import alex.guerra.cinetix.data.database.MovieDatabase
import alex.guerra.cinetix.data.database.RoomDataSource
import alex.guerra.cinetix.data.network.RetrofitDataSource
import alex.guerra.data.repository.MoviesRepository
import alex.guerra.data.repository.RegionRepository
import alex.guerra.usecases.GetPopularMovies
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room

class MainViewModelFactory(private val application: CinetixApp) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val playServicesLocationDataSource =
            PlayServicesLocationDataSource(
                application
            )
        val checker = AndroidPermissionChecker(application)
        val regionRepository =
            RegionRepository(
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

        val getPopularMovies = GetPopularMovies(movieRepository)

        return modelClass.getConstructor(GetPopularMovies::class.java)
            .newInstance(getPopularMovies)

        throw IllegalArgumentException("wrong dependencies")
    }
}
