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
import kotlinx.android.synthetic.main.fragment_movies_details.*

class FragmentMoviesDetails : Fragment() {

    private var rvActors: RecyclerView? = null
    private var movie: Movie? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_movies_details, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        movie = arguments?.getParcelable(MOVIE_KEY)
        val actors: List<Actor>? = movie?.actors
        rvActors = view.findViewById(R.id.recViewActors)
        val adapter = ActorsAdapter(view.context, actors)
        rvActors?.adapter = adapter
        rvActors?.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        Glide.with(view.context)
            .load(movie?.movieImage)
            .optionalCenterCrop()
            .into(orig)

        view.findViewById<View>(R.id.buttonBack)
            .setOnClickListener {
                activity?.onBackPressed()
            }
    }

    companion object {
        private const val MOVIE_KEY = "movie"

        fun newInstance(movie: Movie): FragmentMoviesDetails {
            val fragment = FragmentMoviesDetails()
            var args = Bundle()
            args.putParcelable(MOVIE_KEY, movie)
            fragment.arguments = args
            return fragment
        }
    }

}