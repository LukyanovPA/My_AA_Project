package com.pavellukyanov.myaaproject.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.pavellukyanov.myaaproject.R
import com.pavellukyanov.myaaproject.data.Movie
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.view_holder_movie.view.*

class MovieListAdapter(
    private val clickListener: ItemClickListener
) : RecyclerView.Adapter<MovieListViewHolder>() {

    var movies: List<Movie> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListViewHolder {
        val inflater =
            LayoutInflater.from(parent.context).inflate(R.layout.view_holder_movie, parent, false)
        return MovieListViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: MovieListViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.itemView.setOnClickListener {
            clickListener.onItemClicked(getItem(position))
        }
    }

    override fun getItemCount(): Int = movies.size

    fun getItem(position: Int): Movie = movies[position]
}

class MovieListViewHolder(override val containerView: View): RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bind(movie: Movie) {
            Glide.with(containerView.context)
                .load(movie.poster)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .into(containerView.poster)

        val resId: Int = if (movie.numberOfRatings > 1000) {
            R.drawable.vector_like
        } else {
            R.drawable.vector_no_like
        }

        val geners = arrayListOf<String>()
        movie.genres.forEach { i ->
            geners.add(i.name)
        }

        with(containerView) {
            likeView.setImageResource(resId)
            minimumAge.text = context.getString(R.string.some_id, movie.minimumAge)
            genres.text = geners.joinToString()
            ratingBar.rating = movie.ratings.div(2)
            numberOfRatings.text = context.getString(R.string.votes, movie.numberOfRatings)
            movieName.text = movie.title
            timeMovie.text = context.getString(R.string.movie_time, movie.runtime)
        }
    }

}

interface ItemClickListener {
    fun onItemClicked(movie: Movie)
}
