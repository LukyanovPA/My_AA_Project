package com.pavellukyanov.myaaproject.ui.views

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.pavellukyanov.myaaproject.R
import com.pavellukyanov.myaaproject.data.models.old.Actor
import com.pavellukyanov.myaaproject.data.models.Movie
import com.pavellukyanov.myaaproject.ui.adapters.ActorsAdapter

class FragmentMoviesDetails : Fragment(R.layout.fragment_movies_details) {

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        val movie: Movie = requireNotNull(requireArguments().getParcelable(MOVIE_KEY))
//        initAdapter(movie.actors)
//        changeUI(movie)
//    }
//
//    private fun initAdapter(actors: List<Actor>) {
//        val adapter = ActorsAdapter()
//        actors.let { adapter.actors = it }
//        recViewActors.adapter = adapter
//        recViewActors.layoutManager =
//            LinearLayoutManager(
//                context,
//                LinearLayoutManager.HORIZONTAL,
//                false
//            )
//    }
//
//    private fun changeUI(movie: Movie) {
//            Glide.with(requireContext())
//                .load(movie.poster)
//                .centerCrop()
//                .into(orig)
//
//        val geners = arrayListOf<String>()
//        movie.genres.forEach { i ->
//            geners.add(i.name)
//        }
//
//        buttonBack.setOnClickListener { activity?.onBackPressed() }
//
//        with(movie) {
//            ageLevel.text = context?.getString(R.string.some_id, minimumAge)
//            tvName.text = title
//            tagLine.text = geners.joinToString()
//            ratingBarDetails.rating = ratings.div(2)
//            votes.text = context?.getString(R.string.votes, numberOfRatings)
//            storyLineText.text = overview
//        }
//    }
//
//    companion object {
//        private const val MOVIE_KEY = "movie"
//
//        fun newInstance(movie: Movie): Fragment = FragmentMoviesDetails().apply {
//            arguments = bundleOf(MOVIE_KEY to movie)
//        }
//    }

}