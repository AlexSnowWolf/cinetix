package alex.guerra.cinetix.ui.main.di

import alex.guerra.cinetix.ui.main.MainViewModel
import dagger.Subcomponent

@Subcomponent(modules = [MainActivityModule::class])
interface MainActivityComponent {
    val mainViewModel: MainViewModel
}
