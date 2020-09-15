package alex.guerra.cinetix.ui.detail.di

import alex.guerra.cinetix.ui.detail.DetailViewModel
import dagger.Subcomponent

@Subcomponent(modules = [DetailActivityModule::class])
interface DetailActivityComponent {
    val detailViewModel: DetailViewModel
}
