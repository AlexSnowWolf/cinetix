package alex.guerra.cinetix.ui

import alex.guerra.cinetix.databinding.ActivityMainBinding
import alex.guerra.cinetix.framework.viewmodels.MainViewModel
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val movieAdapter = MoviesAdapter()
    private val viewModel: MainViewModel by viewModels()

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

            when(result){
                true -> binding.progressBar.visibility = View.VISIBLE
                false -> binding.progressBar.visibility = View.GONE
            }
        })
    }

    private fun setup() {
        binding.recyclerView.apply { adapter = movieAdapter }
        viewModel.getRemotePopularMovies()

        //TODO I need to add location flow to get region
    }
}