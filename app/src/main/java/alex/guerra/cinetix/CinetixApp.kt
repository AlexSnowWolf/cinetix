package alex.guerra.cinetix

import alex.guerra.cinetix.data.database.MovieDatabase
import android.app.Application
import androidx.room.Room

class CinetixApp : Application() {
    lateinit var db: MovieDatabase
        private set

    override fun onCreate() {
        super.onCreate()

        db = Room.databaseBuilder(
            this,
            MovieDatabase::class.java, "movie-db"
        ).build()
    }
}
