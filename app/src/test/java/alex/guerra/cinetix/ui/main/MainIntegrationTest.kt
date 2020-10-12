package alex.guerra.cinetix.ui.main

import alex.guerra.cinetix.FakeLocalDataSource
import alex.guerra.cinetix.defaultFakeMovies
import alex.guerra.cinetix.initMockedDi
import alex.guerra.data.source.LocalDataSource
import alex.guerra.testshared.mockedMovie
import alex.guerra.usecases.GetPopularMovies
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.verify
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.dsl.module
import org.koin.test.AutoCloseKoinTest
import org.koin.test.get
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MainIntegrationTest : AutoCloseKoinTest() {

    @get:Rule
    var rule = InstantTaskExecutorRule()

    @Mock
    lateinit var observer: Observer<MainViewModel.UiModel>

    private lateinit var mainViewModel: MainViewModel

    @Before
    fun setUp() {
        val viewModelModule = module {
            factory { MainViewModel(get(), get()) }
            factory { GetPopularMovies(get()) }
        }

        initMockedDi(viewModelModule)
        mainViewModel = get()
    }

    @Test
    fun `data is loaded from server when local source is empty`() {
        mainViewModel.model.observeForever(observer)

        mainViewModel.getRemotePopularMovies()

        verify(observer).onChanged(MainViewModel.UiModel.Content(defaultFakeMovies))
    }

    @Test
    fun `data is loaded from local source when available`() {
        val fakeLocalMovies = listOf(mockedMovie.copy(10), mockedMovie.copy(11))
        val localDataSource = get<LocalDataSource>() as FakeLocalDataSource
        localDataSource.movies = fakeLocalMovies
        mainViewModel.model.observeForever(observer)

        mainViewModel.getRemotePopularMovies()

        verify(observer).onChanged(MainViewModel.UiModel.Content(fakeLocalMovies))
    }
}
