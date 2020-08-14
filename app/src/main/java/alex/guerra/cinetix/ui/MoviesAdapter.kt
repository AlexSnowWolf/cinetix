package alex.guerra.cinetix.ui

import alex.guerra.cinetix.R
import alex.guerra.cinetix.databinding.MovieItemBinding
import alex.guerra.cinetix.domain.MovieEntity
import alex.guerra.cinetix.domain.MovieServer
import alex.guerra.cinetix.framework.inflate
import alex.guerra.cinetix.framework.loadUrl
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class MoviesAdapter(private val clickListener: (MovieEntity) -> Unit) :
    ListAdapter<MovieEntity, MoviesAdapter.MovieViewHolder>(MoviesDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder =
        MovieViewHolder(parent.inflate(R.layout.movie_item))

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(getItem(position), clickListener)
    }

    class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = MovieItemBinding.bind(view)

        fun bind(movie: MovieEntity, clickListener: (MovieEntity) -> Unit) {
            with(binding) {
                imMovieItem.loadUrl("https://image.tmdb.org/t/p/w185/${movie.posterPath}")
                tvTitleMovieItem.text = movie.title
                tvRatioMovieItem.text = movie.voteAverage.toString()
                cvMovieItem.setOnClickListener { clickListener(movie) }
            }
        }
    }
}

class MoviesDiffCallback : DiffUtil.ItemCallback<MovieEntity>() {
    override fun areItemsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean =
        oldItem == newItem
}
