package alex.guerra.cinetix.data

import alex.guerra.data.repository.PermissionChecker
import alex.guerra.data.repository.PermissionChecker.Permission
import android.Manifest
import android.app.Application
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat

class AndroidPermissionChecker(private val application: Application) : PermissionChecker {
    override suspend fun check(permission: Permission) =
        ContextCompat.checkSelfPermission(
            application,
            permission.toAndroidId()
        ) == PackageManager.PERMISSION_GRANTED
}

private fun Permission.toAndroidId() = when (this) {
    Permission.COARSE_LOCATION -> Manifest.permission.ACCESS_COARSE_LOCATION
}
