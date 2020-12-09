package com.pavellukyanov.myaaproject.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.pavellukyanov.myaaproject.R
import com.pavellukyanov.myaaproject.data.Actor
import com.pavellukyanov.myaaproject.data.Movie
import com.pavellukyanov.myaaproject.holders.ActorsAdapter

class FragmentMoviesDetails : Fragment() {
    companion object {
        private const val ARG_MOVIE = "movie"

        fun newInstance(movie: Movie): FragmentMoviesDetails {
            return FragmentMoviesDetails().apply {
                arguments = bundleOf(
                    ARG_MOVIE to movie
                )
            }
        }
    }

    private var rvActors: RecyclerView? = null
    private var movie: Movie? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_movies_details, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        movie = arguments?.getParcelable(ARG_MOVIE)
        val actors: List<Actor>? = movie?.actors
        rvActors = view.findViewById(R.id.recViewActors)
        val adapter = ActorsAdapter(view.context, actors)
        rvActors?.adapter = adapter
        rvActors?.layoutManager = LinearLayoutManager(view.context)

        val moviePicture: ImageView = view.findViewById(R.id.orig)
        Glide.with(view.context)
            .load(movie?.movieImage)
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .into(moviePicture)

        view.findViewById<View>(R.id.buttonBack)
            .setOnClickListener {
                activity?.onBackPressed()
            }
    }

}