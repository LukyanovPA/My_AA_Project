package com.pavellukyanov.myaaproject.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.pavellukyanov.myaaproject.R
import com.pavellukyanov.myaaproject.data.models.Movie
import com.pavellukyanov.myaaproject.databinding.ViewHolderMovieBinding
import com.pavellukyanov.myaaproject.utils.RuntimeMovie

class MovieListAdapter(
    private val movies: List<Movie>,
    private val clickListener: ItemClickListener
) : RecyclerView.Adapter<MovieListAdapter.MovieListViewHolder>() {
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

        fun bind(movie: Movie) {
            Glide.with(itemView.context)
                .load(movie.moviePoster)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .into(binding.poster)

            with(binding) {
                val resId: Int = if (movie.voteCount > 1000) {
                    R.drawable.vector_like
                } else {
                    R.drawable.vector_no_like
                }

                val rating: Float = (movie.voteAverage / 2).toFloat()

                likeView.setImageResource(resId)
                minimumAge.text = itemView.context.getString(R.string.some_id, movie.minimumAge)
                genres.text = movie.genres.joinToString(separator = ", ")
                ratingBar.rating = rating
                numberOfRatings.text = itemView.context.getString(R.string.votes, movie.voteCount)
                movieName.text = movie.title
            }
        }

    }
}

interface ItemClickListener {
    fun onItemClicked(movie: Movie)
}
