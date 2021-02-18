package com.pavellukyanov.myaaproject.ui.views

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.pavellukyanov.myaaproject.R
import com.pavellukyanov.myaaproject.data.api.MoviesRemoteRepo
import com.pavellukyanov.myaaproject.data.api.Router
import com.pavellukyanov.myaaproject.data.models.Credits
import com.pavellukyanov.myaaproject.data.models.Movie
import com.pavellukyanov.myaaproject.databinding.FragmentMoviesDetailsBinding
import com.pavellukyanov.myaaproject.ui.adapters.ActorsAdapter
import com.pavellukyanov.myaaproject.ui.base.DetailsViewModelFactory
import com.pavellukyanov.myaaproject.ui.viewmodels.MovieDetailsViewModel
import com.pavellukyanov.myaaproject.utils.Status

class FragmentMoviesDetails : Fragment(R.layout.fragment_movies_details) {
    private val detailsViewModel: MovieDetailsViewModel by viewModels {
        DetailsViewModelFactory(MoviesRemoteRepo(Router().movieApi))
    }
    private lateinit var movie: Movie
    private lateinit var binding: FragmentMoviesDetailsBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        movie = requireNotNull(requireArguments()).getParcelable(MOVIE)!!
        binding = FragmentMoviesDetailsBinding.bind(view)
        subscribeMovieDetailsViewModel(movie.id)
        changeUI(movie)
    }

    private fun subscribeMovieDetailsViewModel(movieId: Int) {
        detailsViewModel.getMovieCredits(movieId).observe(this.viewLifecycleOwner, { resourceMovieCredits ->
            resourceMovieCredits?.let {
                when(resourceMovieCredits.status) {
                    Status.SUCCESS -> {
                        resourceMovieCredits.data?.let { initCreditsAdapter(it) }
                    }
                }
            }
        })
    }

    private fun initCreditsAdapter(credits: Credits) {
        val adapter = ActorsAdapter()
        binding.recViewActors.adapter = adapter
        binding.recViewActors.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        adapter.actors = credits.cast
    }

    private fun changeUI(movie: Movie) {
        Glide.with(requireContext())
            .load(movie.moviePoster)
            .centerCrop()
            .into(binding.orig)

        val rating: Float = (movie.voteAverage / 2).toFloat()

        with(binding) {
            buttonBack.setOnClickListener { activity?.onBackPressed() }
            ageLevel.text = context?.getString(R.string.some_id, movie.minimumAge)
            tvName.text = movie.originalTitle
            tagLine.text = movie.genres.joinToString(separator = ", ")
            ratingBarDetails.rating = rating
            votes.text = context?.getString(R.string.votes, movie.voteCount)
            storyLineText.text = movie.overview
        }
    }

    companion object {
        private const val MOVIE = "movie"
        fun newInstance(movie: Movie): Fragment = FragmentMoviesDetails().apply {
            arguments = Bundle().apply { putParcelable(MOVIE, movie) }
        }
    }
}
