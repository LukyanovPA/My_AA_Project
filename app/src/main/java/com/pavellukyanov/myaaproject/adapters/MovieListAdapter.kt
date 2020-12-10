package com.pavellukyanov.myaaproject.adapters

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

    private val movieImage: ImageView? = itemView.findViewById(R.id.imageView)
    private val someID: TextView? = itemView.findViewById(R.id.tvSomeID)
    private val tag: TextView? = itemView.findViewById(R.id.tag)
    private val ratingBar: RatingBar? = itemView.findViewById(R.id.ratingBar)
    private val reviews: TextView? = itemView.findViewById(R.id.reviews)
    private val movieName: TextView? = itemView.findViewById(R.id.movieName)
    private val timeMovie: TextView? = itemView.findViewById(R.id.timeMovie)

    fun bind(movie: Movie) {
        movieImage?.let {
            Glide.with(itemView.context)
                .load(movie.movieImage)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .into(movieImage)
        }

        someID?.text = movie.someID
        tag?.text = movie.tag
        ratingBar?.rating = movie.rating
        reviews?.text = movie.reviews
        movieName?.text = movie.name
        timeMovie?.text = movie.movieTime
    }

}

interface ItemClickListener {
    fun onItemClicked(movie: Movie)
}
