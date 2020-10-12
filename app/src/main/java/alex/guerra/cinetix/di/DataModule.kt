package alex.guerra.cinetix.di

import alex.guerra.data.repository.MoviesRepository
import alex.guerra.data.repository.PermissionChecker
import alex.guerra.data.repository.RegionRepository
import alex.guerra.data.source.LocalDataSource
import alex.guerra.data.source.LocationDataSource
import alex.guerra.data.source.RemoteDataSource
import dagger.Module
import dagger.Provides

@Module
class DataModule {
    @Provides
    fun regionRepositoryProvider(
        locationDataSource: LocationDataSource,
        permissionChecker: PermissionChecker
    ) = RegionRepository(locationDataSource, permissionChecker)

    @Provides
    fun moviesRepositoryProvider(
        localDataSource: LocalDataSource,
        remoteDataSource: RemoteDataSource,
        regionRepository: RegionRepository
    ) = MoviesRepository(localDataSource, remoteDataSource, regionRepository)
}
