package alex.guerra.cinetix

import alex.guerra.cinetix.di.DaggerMyMoviesComponent
import alex.guerra.cinetix.di.MyMoviesComponent
import android.app.Application

class CinetixApp : Application() {

    lateinit var component: MyMoviesComponent
        private set

    override fun onCreate() {
        super.onCreate()
        component = DaggerMyMoviesComponent
            .factory()
            .create(this)
    }
}
