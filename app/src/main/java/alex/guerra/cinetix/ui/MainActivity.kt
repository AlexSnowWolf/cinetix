package alex.guerra.cinetix.ui

import alex.guerra.cinetix.databinding.ActivityMainBinding
import alex.guerra.cinetix.domain.MovieRemote
import alex.guerra.cinetix.framework.showPermissionRequest
import alex.guerra.cinetix.framework.startActivity
import alex.guerra.cinetix.framework.viewmodels.MainViewModel
import alex.guerra.cinetix.framework.viewmodels.MainViewModelFactory
import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val movieAdapter = MoviesAdapter(::goToDetail)
    // private val viewModel: MainViewModel by viewModels()
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setup()
        initObservers()
    }

    private fun initObservers() {
        viewModel.movies.observe(this, Observer { movies ->
            movieAdapter.submitList(movies)
        })

        viewModel.loading.observe(this, Observer { result ->
            when (result) {
                true -> binding.progressBar.visibility = View.VISIBLE
                false -> binding.progressBar.visibility = View.GONE
            }
        })
    }

    private fun setup() {
        viewModel = ViewModelProvider(this, MainViewModelFactory(this))
            .get(MainViewModel::class.java)

        binding.recyclerView.apply { adapter = movieAdapter }
        showPermissionRequest(
            Manifest.permission.ACCESS_COARSE_LOCATION,
            this,
            LOCATION_PERMISSION_CODE,
            viewModel::getRemotePopularMovies
        )
    }

    private fun goToDetail(movie: MovieRemote) {
        startActivity<DetailActivity> {
            putExtra(DetailActivity.MOVIE, movie)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            LOCATION_PERMISSION_CODE -> {
                if ((grantResults.isNotEmpty()) &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED
                ) {
                    viewModel.getRemotePopularMovies()
                }
            }
        }
    }

    companion object {
        private const val LOCATION_PERMISSION_CODE = 100
    }
}
