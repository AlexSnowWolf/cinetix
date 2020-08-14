package alex.guerra.cinetix.ui

import alex.guerra.cinetix.databinding.ActivityDetailBinding
import alex.guerra.cinetix.domain.MovieServer
import alex.guerra.cinetix.framework.loadUrl
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.bold
import androidx.core.text.buildSpannedString

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        /*



        intent.getParcelableExtra<MovieServer>(MOVIE)?.run {
            with(binding) {
                movieDetailToolbar.title = title
                val background = backdropPath ?: posterPath
                movieDetailImage.loadUrl("https://image.tmdb.org/t/p/w780$background")
                tvMovieDetailSummary.text = overview
                tvMovieDetailInfo.text = buildSpannedString {

                    bold { append("Original language: ") }
                    appendln(originalLanguage)

                    bold { append("Original title: ") }
                    appendln(originalTitle)

                    bold { append("Release date: ") }
                    appendln(releaseDate)

                    bold { append("Popularity: ") }
                    appendln(popularity.toString())

                    bold { append("Vote Average: ") }
                    append(voteAverage.toString())
                }
            }
        }

         */

    }

    companion object {
        const val MOVIE = "DetailActivity:movie"
    }
}
