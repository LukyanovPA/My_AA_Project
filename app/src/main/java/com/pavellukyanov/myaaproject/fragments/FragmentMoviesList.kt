package com.pavellukyanov.myaaproject.fragments

import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.pavellukyanov.myaaproject.R
import com.pavellukyanov.myaaproject.adapters.ItemClickListener
import com.pavellukyanov.myaaproject.adapters.MovieListAdapter
import com.pavellukyanov.myaaproject.data.Movie
import com.pavellukyanov.myaaproject.data.loadMovies
import kotlinx.android.synthetic.main.fragment_movies_list.*
import kotlinx.coroutines.*

class FragmentMoviesList : Fragment(R.layout.fragment_movies_list) {

    private val exceptionHandler = CoroutineExceptionHandler { coroutineContext, exception ->
        println("CoroutineExceptionHandler got $exception in $coroutineContext")
    }
    private val scope = CoroutineScope(
        SupervisorJob()
                + Dispatchers.Main
                + exceptionHandler
    )

    override fun onStart() {
        super.onStart()
        scope.launch { goNewCoroutine() }
    }

    private suspend fun goNewCoroutine() {
        val items: List<Movie> = scope.async { loadMovies(requireContext()) }.await()
        initRecycler(items)
    }

    private fun initRecycler(movies: List<Movie>) {
            val adapter = MovieListAdapter(clickListener)
            adapter.movies = movies
            recViewMovieList.adapter = adapter
            recViewMovieList.layoutManager = GridLayoutManager(context, 2)
    }

    private val clickListener = object : ItemClickListener {
        override fun onItemClicked(movie: Movie) {
            showMoviesDetailsFragment(movie)
        }
    }

    private fun showMoviesDetailsFragment(movie: Movie) {
        requireFragmentManager().beginTransaction()
            .replace(R.id.flFragmetn, FragmentMoviesDetails.newInstance(movie))
            .addToBackStack("FragmentMovieDetails")
            .commit()
    }
}