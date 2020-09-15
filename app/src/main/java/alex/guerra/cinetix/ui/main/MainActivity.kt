package alex.guerra.cinetix.ui.main

import alex.guerra.cinetix.databinding.ActivityMainBinding
import alex.guerra.cinetix.ui.common.app
import alex.guerra.cinetix.ui.common.getViewModel
import alex.guerra.cinetix.ui.common.showPermissionRequest
import alex.guerra.cinetix.ui.common.startActivity
import alex.guerra.cinetix.ui.detail.DetailActivity
import alex.guerra.cinetix.ui.main.MainViewModel.UiModel
import alex.guerra.cinetix.ui.main.di.MainActivityComponent
import alex.guerra.cinetix.ui.main.di.MainActivityModule
import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    // private val viewModel: MainViewModel by viewModels { MainViewModelFactory(app) }
    private lateinit var component: MainActivityComponent
    private val viewModel: MainViewModel by lazy { getViewModel { component.mainViewModel } }

    private lateinit var movieAdapter: MoviesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setup()
        initObservers()
    }

    private fun initObservers() {
        viewModel.model.observe(this, Observer(::updateUi))

        viewModel.navigation.observe(this, Observer { event ->
            event.getContentIfNotHandled()?.let { movie ->
                startActivity<DetailActivity> {
                    putExtra(DetailActivity.MOVIE, movie.id)
                }
            }
        })
    }

    private fun updateUi(model: UiModel) {
        binding.progressBar.visibility = if (model is UiModel.Loading) View.VISIBLE else View.GONE

        when (model) {
            is UiModel.Content -> movieAdapter.submitList(model.movies)
            is UiModel.RequestLocationPermission -> showPermissionRequest(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                LOCATION_PERMISSION_CODE,
                viewModel::getRemotePopularMovies
            )
        }
    }

    private fun setup() {
        component = app.component.plus(MainActivityModule())
        movieAdapter =
            MoviesAdapter(viewModel::onMovieClicked)
        binding.recyclerView.apply { adapter = movieAdapter }
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
