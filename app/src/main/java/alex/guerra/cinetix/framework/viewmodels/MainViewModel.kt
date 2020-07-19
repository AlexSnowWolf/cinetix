package alex.guerra.cinetix.framework.viewmodels

import alex.guerra.cinetix.domain.MovieRemote
import alex.guerra.cinetix.framework.UseCase
import alex.guerra.cinetix.framework.networking.TmdbClient
import alex.guerra.cinetix.repository.MovieRepository
import alex.guerra.cinetix.repository.RemoteDataSource
import alex.guerra.cinetix.usecase.GetPopularMovies
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val repository = MovieRepository(RemoteDataSource())
    private val useCase = UseCase(getPopularMovies = GetPopularMovies(repository))
    private val _movies = MutableLiveData<List<MovieRemote>>()
    val movies : LiveData<List<MovieRemote>> get() = _movies
    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading

    fun getRemotePopularMovies() {
        viewModelScope.launch {
            _loading.value = true
            val result = useCase.getPopularMovies("MX")
            _movies.value = result.results
            _loading.value = false
        }
    }
}