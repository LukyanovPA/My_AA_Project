package com.pavellukyanov.myaaproject.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.pavellukyanov.myaaproject.R
import com.pavellukyanov.myaaproject.data.models.Movie
import com.pavellukyanov.myaaproject.databinding.ViewHolderMovieBinding

class MovieListAdapter(
    private val movies: List<Movie>,
    private val clickListener: ItemClickListener
) : RecyclerView.Adapter<MovieListAdapter.MovieListViewHolder>() {

//    var movies: List<Movie> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListViewHolder {
        val binding =
            ViewHolderMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieListViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.itemView.setOnClickListener {
            clickListener.onItemClicked(getItem(position))
        }
    }

    override fun getItemCount(): Int = movies.size

    private fun getItem(position: Int): Movie = movies[position]

    class MovieListViewHolder(private val binding: ViewHolderMovieBinding) : RecyclerView.ViewHolder(binding.root) {
//    private val binding = ViewHolderMovieBinding.bind(view)

        fun bind(movie: Movie) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(movie.moviePoster)
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                    .into(poster)

                val resId: Int = if (movie.voteCount > 1000) {
                    R.drawable.vector_like
                } else {
                    R.drawable.vector_no_like
                }

                likeView.setImageResource(resId)
                minimumAge.append(itemView.context.getString(R.string.some_id, movie.minimumAge))
                genres.append(movie.genre.toString())
                ratingBar.rating = movie.popularity.toFloat()
                numberOfRatings.append(itemView.context.getString(R.string.votes, movie.voteCount))
                movieName.append(movie.title)
                timeMovie.append(itemView.context.getString(R.string.movie_time, 2.30))
            }
        }

    }
}

interface ItemClickListener {
    fun onItemClicked(movie: Movie)
}
