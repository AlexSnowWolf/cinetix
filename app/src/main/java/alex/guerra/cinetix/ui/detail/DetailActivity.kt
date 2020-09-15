package alex.guerra.cinetix.ui.detail

import alex.guerra.cinetix.R
import alex.guerra.cinetix.databinding.ActivityDetailBinding
import alex.guerra.cinetix.ui.common.app
import alex.guerra.cinetix.ui.common.getViewModel
import alex.guerra.cinetix.ui.common.loadUrl
import alex.guerra.cinetix.ui.detail.di.DetailActivityComponent
import alex.guerra.cinetix.ui.detail.di.DetailActivityModule
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.text.bold
import androidx.core.text.buildSpannedString
import androidx.lifecycle.Observer

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var component: DetailActivityComponent
    private val viewModel: DetailViewModel by lazy { getViewModel { component.detailViewModel } }
    /*
    private val viewModel: DetailViewModel by viewModels {
        DetailViewModelFactory(
            intent.getIntExtra(MOVIE, -1), app
        )
    }
     */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setUp()
        initObservers()
    }

    private fun setUp() {
        component = app.component.plus(DetailActivityModule(intent.getIntExtra(MOVIE, -1)))
        binding.movieDetailFavorite.setOnClickListener { viewModel.onFavoriteClicked() }
    }

    private fun initObservers() {
        viewModel.model.observe(this, Observer(::updateUi))
    }

    private fun updateUi(model: DetailViewModel.UiModel) {
        with(binding) {
            val icon =
                if (model.movie.favorite) R.drawable.ic_favorite_checked else R.drawable.ic_favorite

            movieDetailFavorite.setImageDrawable(
                ContextCompat.getDrawable(
                    this@DetailActivity,
                    icon
                )
            )
            movieDetailToolbar.title = model.movie.title
            val background = model.movie.backdropPath
            movieDetailImage.loadUrl("https://image.tmdb.org/t/p/w780$background")
            tvMovieDetailSummary.text = model.movie.overview
            tvMovieDetailInfo.text = buildSpannedString {

                bold { append("Original language: ") }
                appendln(model.movie.originalLanguage)

                bold { append("Original title: ") }
                appendln(model.movie.originalTitle)

                bold { append("Release date: ") }
                appendln(model.movie.releaseDate)

                bold { append("Popularity: ") }
                appendln(model.movie.popularity.toString())

                bold { append("Vote Average: ") }
                append(model.movie.voteAverage.toString())
            }
        }
    }

    companion object {
        const val MOVIE = "DetailActivity:movie"
    }
}
