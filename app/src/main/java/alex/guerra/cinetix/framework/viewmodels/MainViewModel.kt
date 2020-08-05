package alex.guerra.cinetix.framework.viewmodels

import alex.guerra.cinetix.domain.MovieRemote
import alex.guerra.cinetix.framework.UseCase
import alex.guerra.cinetix.repository.MovieRepository
import alex.guerra.cinetix.repository.RegionRepository
import alex.guerra.cinetix.usecase.GetPopularMovies
import alex.guerra.cinetix.usecase.GetRegion
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel(
    movieRepository: MovieRepository,
    regionRepository: RegionRepository
) : ViewModel() {
    // private val repository = MovieRepository(RemoteDataSource())
    private val useCase = UseCase(
        getPopularMovies = GetPopularMovies(movieRepository),
        getRegion = GetRegion(regionRepository)
    )
    private val _movies = MutableLiveData<List<MovieRemote>>()
    val movies: LiveData<List<MovieRemote>> get() = _movies
    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading

    fun getRemotePopularMovies() {
        viewModelScope.launch {
            _loading.value = true
            val regionResult = useCase.getRegion.invoke()
            val result = useCase.getPopularMovies(regionResult)
            _movies.value = result.results
            _loading.value = false
        }
    }
}
