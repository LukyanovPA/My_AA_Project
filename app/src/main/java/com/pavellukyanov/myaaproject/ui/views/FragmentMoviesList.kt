package com.pavellukyanov.myaaproject.ui.views

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.pavellukyanov.myaaproject.R
import com.pavellukyanov.myaaproject.data.api.MoviesRemoteRepo
import com.pavellukyanov.myaaproject.data.api.Router
import com.pavellukyanov.myaaproject.data.models.Movie
import com.pavellukyanov.myaaproject.ui.adapters.ItemClickListener
import com.pavellukyanov.myaaproject.ui.adapters.MovieListAdapter
import com.pavellukyanov.myaaproject.databinding.FragmentMoviesListBinding
import com.pavellukyanov.myaaproject.ui.base.MovieListViewModelFactory
import com.pavellukyanov.myaaproject.ui.viewmodels.MovieListViewModel
import com.pavellukyanov.myaaproject.utils.MovieCategory

class FragmentMoviesList : Fragment(R.layout.fragment_movies_list) {
    private lateinit var movieListViewModel: MovieListViewModel
    private lateinit var binding: FragmentMoviesListBinding

    override fun onStart() {
        super.onStart()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMoviesListBinding.bind(view)
        initViewModel()
        val category = MovieCategory.UPCOMING
        subscribeViewModel(category)
    }

    private fun subscribeViewModel(category: MovieCategory) {
        movieListViewModel.getMovie(category).observe(this.viewLifecycleOwner, {response ->
            initRecycler(response)
        })
    }

    private fun initViewModel() {
        val factory = MovieListViewModelFactory(MoviesRemoteRepo(Router().movieApi))
        movieListViewModel = ViewModelProvider(this, factory).get(MovieListViewModel::class.java)
    }

    private fun initRecycler(movies: List<Movie>) {
        val adapter = MovieListAdapter(movies, clickListener)
        binding.recViewMovieList.adapter = adapter
        binding.recViewMovieList.layoutManager = GridLayoutManager(context, COLUMN)
    }

    private val clickListener = object : ItemClickListener {
        override fun onItemClicked(movie: Movie) {
//            showMoviesDetailsFragment(movie)
        }
    }

//        private fun showMoviesDetailsFragment(movie: Movie) {
//        requireFragmentManager().beginTransaction()
//            .replace(R.id.flFragmetn, FragmentMoviesDetails.newInstance(movie))
//            .addToBackStack("FragmentMovieDetails")
//            .commit()
//    }

    companion object {
        private const val COLUMN = 2

        fun newInstance(): Fragment = FragmentMoviesList()
        }
    }
