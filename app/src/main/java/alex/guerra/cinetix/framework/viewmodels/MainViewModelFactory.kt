package alex.guerra.cinetix.framework.viewmodels

import alex.guerra.cinetix.framework.PermissionChecker
import alex.guerra.cinetix.repository.MovieRepository
import alex.guerra.cinetix.repository.PlayServicesLocationDataSource
import alex.guerra.cinetix.repository.RegionRepository
import alex.guerra.cinetix.repository.RemoteDataSource
import android.Manifest
import android.content.Context
import android.location.Geocoder
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MainViewModelFactory(private val context: Context) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val playServicesLocationDataSource = PlayServicesLocationDataSource(context)
        val checker = PermissionChecker(context, Manifest.permission.ACCESS_COARSE_LOCATION)
        val geocoder = Geocoder(context)
        val regionRepository = RegionRepository(playServicesLocationDataSource, checker, geocoder)
        val movieRepository = MovieRepository(RemoteDataSource())

        return modelClass.getConstructor(MovieRepository::class.java, RegionRepository::class.java)
            .newInstance(movieRepository, regionRepository)

        throw IllegalArgumentException("wrong dependencies")
    }
}
