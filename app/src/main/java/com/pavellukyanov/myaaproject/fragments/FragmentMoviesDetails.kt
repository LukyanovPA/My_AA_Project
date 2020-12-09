package com.pavellukyanov.myaaproject.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.pavellukyanov.myaaproject.R
import com.pavellukyanov.myaaproject.data.Actor
import com.pavellukyanov.myaaproject.data.Movie
import com.pavellukyanov.myaaproject.adapters.ActorsAdapter
import kotlinx.android.synthetic.main.fragment_movies_details.*

class FragmentMoviesDetails : Fragment() {
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

        val adapter = ActorsAdapter(view.context, actors)
        recViewActors.adapter = adapter
        recViewActors.layoutManager =
            LinearLayoutManager(
                context,
                LinearLayoutManager.HORIZONTAL,
                false
            )

        changeUI(view, movie)
    }

    private fun changeUI(view: View, movie: Movie?) {
        Glide.with(view.context)
            .load(movie?.movieImage)
            .centerCrop()
            .into(orig)

        buttonBack.setOnClickListener {
                activity?.onBackPressed()
            }
        some_id.text = movie?.someID
        tvName.text = movie?.name
        tagLine.text = movie?.tag
        ratingBarDetails.rating = movie!!.rating
        votes.text = movie.reviews
    }

    companion object {
        private const val MOVIE_KEY = "movie"

        fun newInstance(movie: Movie): FragmentMoviesDetails {
            val fragment = FragmentMoviesDetails()
            val args = Bundle()
            args.putParcelable(MOVIE_KEY, movie)
            fragment.arguments = args
            return fragment
        }
    }

}