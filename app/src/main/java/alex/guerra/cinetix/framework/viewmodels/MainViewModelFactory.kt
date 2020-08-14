package alex.guerra.cinetix.framework.viewmodels

import alex.guerra.cinetix.CinetixApp
import alex.guerra.cinetix.framework.PermissionChecker
import alex.guerra.cinetix.repository.*
import android.Manifest
import android.app.Application
import android.content.Context
import android.location.Geocoder
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MainViewModelFactory(private val application: CinetixApp) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val playServicesLocationDataSource = PlayServicesLocationDataSource(application)
        val checker = PermissionChecker(application, Manifest.permission.ACCESS_COARSE_LOCATION)
        val geocoder = Geocoder(application)
        val regionRepository = RegionRepository(playServicesLocationDataSource, checker, geocoder)
        val localDataSource = LocalDataSource(application)
        val movieRepository = MovieRepository(RemoteDataSource(), localDataSource, regionRepository)

        return modelClass.getConstructor(MovieRepository::class.java)
            .newInstance(movieRepository)

        throw IllegalArgumentException("wrong dependencies")
    }
}
