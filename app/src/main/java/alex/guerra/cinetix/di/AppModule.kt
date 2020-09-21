package alex.guerra.cinetix.di

import alex.guerra.cinetix.data.AndroidPermissionChecker
import alex.guerra.cinetix.data.PlayServicesLocationDataSource
import alex.guerra.cinetix.data.database.MovieDatabase
import alex.guerra.cinetix.data.database.RoomDataSource
import alex.guerra.cinetix.data.network.RetrofitDataSource
import alex.guerra.data.repository.PermissionChecker
import alex.guerra.data.source.LocalDataSource
import alex.guerra.data.source.LocationDataSource
import alex.guerra.data.source.RemoteDataSource
import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun dataBaseProvider(app: Application) =
        Room.databaseBuilder(app, MovieDatabase::class.java, "movieDb").build()

    @Provides
    fun localDataSourceProvider(movieDb: MovieDatabase): LocalDataSource = RoomDataSource(movieDb)

    @Provides
    fun remoteDataSourceProvider(): RemoteDataSource = RetrofitDataSource()

    @Provides
    fun locationDataSourceProvider(app: Application): LocationDataSource =
        PlayServicesLocationDataSource(app)

    @Provides
    fun permissionCheckerProvider(app: Application): PermissionChecker =
        AndroidPermissionChecker(app)

    @Provides
    @Singleton
    fun uiDispatcherProvides(): CoroutineDispatcher = Dispatchers.Main
}
