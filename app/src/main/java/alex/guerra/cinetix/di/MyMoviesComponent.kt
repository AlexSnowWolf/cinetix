package alex.guerra.cinetix.di

import alex.guerra.cinetix.ui.detail.di.DetailActivityComponent
import alex.guerra.cinetix.ui.detail.di.DetailActivityModule
import alex.guerra.cinetix.ui.main.di.MainActivityComponent
import alex.guerra.cinetix.ui.main.di.MainActivityModule
import android.app.Application
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, DataModule::class])
interface MyMoviesComponent {

    fun plus(module: MainActivityModule): MainActivityComponent
    fun plus(module: DetailActivityModule): DetailActivityComponent

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance app: Application): MyMoviesComponent
    }
}
