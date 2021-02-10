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
import kotlinx.coroutines.*

class FragmentMoviesList : Fragment(R.layout.fragment_movies_list) {
    //    private val exceptionHandler = CoroutineExceptionHandler { coroutineContext, exception ->
//        println("CoroutineExceptionHandler got $exception in $coroutineContext")
//    }
//    private val scope = CoroutineScope(
//        SupervisorJob()
//                + Dispatchers.Main
//                + exceptionHandler
//    )
    private lateinit var viewModel: MovieListViewModel
    private lateinit var binding: FragmentMoviesListBinding
    private lateinit var resultList: List<Movie>

//    override fun onStart() {
//        super.onStart()
//        scope.launch { goNewCoroutine() }
//    }

//    private suspend fun goNewCoroutine() {
//        val items: List<Movie> = scope.async { loadMovies(requireContext()) }.await()
//        initRecycler(items)
//    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMoviesListBinding.bind(view)
        initViewModel()
        subscribeOnMovies()
    }

    private fun subscribeOnMovies() {
        viewModel.getMovies().observe(this.viewLifecycleOwner, { movies ->
            movies?.let {
                setObserveData(movies.results)
            }
        })
    }

    private fun setObserveData(data: List<Movie>) {
        resultList = data
        initRecycler(resultList)
    }

    private fun initViewModel() {
        val factory = MovieListViewModelFactory(MoviesRemoteRepo(Router().movieApi))
        viewModel = ViewModelProvider(this, factory).get(MovieListViewModel::class.java)
    }

    private fun initRecycler(movies: List<Movie>) {
        val adapter = MovieListAdapter(clickListener)
        adapter.movies = movies
        binding.recViewMovieList.adapter = adapter
        binding.recViewMovieList.layoutManager = GridLayoutManager(context, 2)
    }

    private val clickListener = object : ItemClickListener {
        override fun onItemClicked(movie: Movie) {
//            showMoviesDetailsFragment(movie)
        }
    }

    //    private fun showMoviesDetailsFragment(movie: Movie) {
//        requireFragmentManager().beginTransaction()
//            .replace(R.id.flFragmetn, FragmentMoviesDetails.newInstance(movie))
//            .addToBackStack("FragmentMovieDetails")
//            .commit()
//    }
    companion object {
        fun newInstance(): Fragment = FragmentMoviesList()
    }
}