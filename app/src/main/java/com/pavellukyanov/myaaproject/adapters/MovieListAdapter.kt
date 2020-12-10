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

class MovieListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val poster: ImageView? = itemView.findViewById(R.id.poster)
    private val minimumAge: TextView? = itemView.findViewById(R.id.minimumAge)
    private val genres: TextView? = itemView.findViewById(R.id.genres)
    private val ratingBar: RatingBar? = itemView.findViewById(R.id.ratingBar)
    private val numberOfRatings: TextView? = itemView.findViewById(R.id.numberOfRatings)
    private val movieName: TextView? = itemView.findViewById(R.id.movieName)
    private val timeMovie: TextView? = itemView.findViewById(R.id.timeMovie)

    fun bind(movie: Movie) {
        poster?.let {
            Glide.with(itemView.context)
                .load(movie.poster)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .into(poster)
        }

        minimumAge?.text = movie.minimumAge.toString()
        genres?.text = movie.genres.toString()

        ratingBar?.rating = movie.ratings
        Log.d("rating", movie.ratings.toString())

        numberOfRatings?.text = movie.numberOfRatings.toString()
        movieName?.text = movie.title
        timeMovie?.text = movie.runtime.toString()
    }

}

interface ItemClickListener {
    fun onItemClicked(movie: Movie)
}
