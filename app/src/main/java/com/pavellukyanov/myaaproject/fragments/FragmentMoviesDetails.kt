package com.pavellukyanov.myaaproject.fragments

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.pavellukyanov.myaaproject.R
import com.pavellukyanov.myaaproject.dataMy.Actor
import com.pavellukyanov.myaaproject.dataMy.Movie
import com.pavellukyanov.myaaproject.adapters.ActorsAdapter
import kotlinx.android.synthetic.main.fragment_movies_details.*

class FragmentMoviesDetails : Fragment(R.layout.fragment_movies_details) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val movie: Movie = requireNotNull(requireArguments().getParcelable(MOVIE_KEY))
        val actors: List<Actor>? = movie.actors

        val adapter = ActorsAdapter()
        actors?.let { adapter.actors = it }
        recViewActors.adapter = adapter
        recViewActors.layoutManager =
            LinearLayoutManager(
                context,
                LinearLayoutManager.HORIZONTAL,
                false
            )

        changeUI(view, movie)
    }

    private fun changeUI(view: View, movie: Movie) {
        Glide.with(view.context)
            .load(movie.movieImage)
            .centerCrop()
            .into(orig)

        buttonBack.setOnClickListener { activity?.onBackPressed() }

        with(movie) {
            minimumAge.text = someID
            tvName.text = name
            tagLine.text = tag
            ratingBarDetails.rating = rating
            votes.text = reviews
        }
    }

    companion object {
        private const val MOVIE_KEY = "movie"

        fun newInstance(movie: Movie): Fragment = FragmentMoviesDetails().apply {
            arguments = bundleOf(MOVIE_KEY to movie)
        }
    }

}