package alex.guerra.cinetix.repository

import alex.guerra.cinetix.framework.PermissionChecker
import android.location.Geocoder
import android.location.Location

class RegionRepository(
    private val locationDataSource: LocationDataSource,
    private val permissionChecker: PermissionChecker,
    private val geocoder: Geocoder
) {

    suspend fun findLastRegion(): String = findLastLocation().toRegion()

    private suspend fun findLastLocation(): Location? {
        val success = permissionChecker.check()
        return if (success) locationDataSource.findLastLocation() else null
    }

    private fun Location?.toRegion(): String {
        val addresses = this?.let {
            geocoder.getFromLocation(latitude, longitude, 1)
        }
        return addresses?.firstOrNull()?.countryCode ?: DEFAULT_REGION
    }

    companion object {
        private const val DEFAULT_REGION = "US"
    }
}
