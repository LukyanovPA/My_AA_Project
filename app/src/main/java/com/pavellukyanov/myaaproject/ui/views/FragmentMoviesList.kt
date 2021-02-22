package com.pavellukyanov.myaaproject.ui.views

import android.app.Application
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.pavellukyanov.myaaproject.R
import com.pavellukyanov.myaaproject.data.api.MoviesRemoteRepo
import com.pavellukyanov.myaaproject.data.api.Router
import com.pavellukyanov.myaaproject.data.database.MovieDataBase
import com.pavellukyanov.myaaproject.data.models.networkmodels.*
import com.pavellukyanov.myaaproject.data.repository.DataBaseRepoImpl
import com.pavellukyanov.myaaproject.data.repository.DataBaseRepoInterface
import com.pavellukyanov.myaaproject.ui.adapters.ItemClickListener
import com.pavellukyanov.myaaproject.ui.adapters.MovieListAdapter
import com.pavellukyanov.myaaproject.databinding.FragmentMoviesListBinding
import com.pavellukyanov.myaaproject.ui.base.MovieListViewModelFactory
import com.pavellukyanov.myaaproject.ui.base.TestViewModelFactory
import com.pavellukyanov.myaaproject.ui.viewmodels.MovieListViewModel
import com.pavellukyanov.myaaproject.ui.viewmodels.TestViewModel
import com.pavellukyanov.myaaproject.utils.MovieCategory
import com.pavellukyanov.myaaproject.utils.Status
import kotlinx.coroutines.*
import java.security.Provider

class FragmentMoviesList : Fragment(R.layout.fragment_movies_list) {
    //test
    private val testModel: TestViewModel by viewModels {
        TestViewModelFactory(MoviesRemoteRepo(Router().movieApi), DataBaseRepoImpl())
    }


    private val movieListViewModel: MovieListViewModel by viewModels {
        MovieListViewModelFactory(MoviesRemoteRepo(Router().movieApi))
    }


    private lateinit var binding: FragmentMoviesListBinding

    override fun onStart() {
        super.onStart()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMoviesListBinding.bind(view)
        //временно
        val category = MovieCategory.POPULAR
        setCategoryName(category)
        subscribeViewModel(category)
    }

    private fun subscribeViewModel(category: MovieCategory) {
        movieListViewModel.getMovieList(category).observe(this.viewLifecycleOwner, { resourceMovieList ->
            resourceMovieList?.let {
                when (resourceMovieList.status) {
                    Status.SUCCESS -> {
                        resourceMovieList.data?.let { it1 -> initRecycler(it1) }
                    }
                }
            }
        })


        //test
        testModel.testStart(9999)
    }

    private fun initRecycler(movies: List<Movie>) {
        val adapter = MovieListAdapter(movies, clickListener)
        binding.recViewMovieList.adapter = adapter
        binding.recViewMovieList.layoutManager = GridLayoutManager(context, COLUMN)
    }

    private val clickListener = object : ItemClickListener {
        override fun onItemClicked(movie: Movie) {
            Log.d("tttt", movie.id.toString())
            showMoviesDetailsFragment(movie)
        }
    }

    private fun showMoviesDetailsFragment(movie: Movie) {
        requireFragmentManager().beginTransaction()
            .replace(R.id.flFragmetn, FragmentMoviesDetails.newInstance(movie))
            .addToBackStack("FragmentMovieDetails")
            .commit()
    }

    private fun setCategoryName(category: MovieCategory) {
        when (category) {
            MovieCategory.POPULAR -> {
                binding.tvCategoryListName.text = context?.getString(R.string.category_popular)
            }
            MovieCategory.TOP_RATED -> {
                binding.tvCategoryListName.text = context?.getString(R.string.category_top_rated)
            }
            MovieCategory.NOW_PLAYING -> {
                binding.tvCategoryListName.text = context?.getString(R.string.category_now_playing)
            }
            MovieCategory.UPCOMING -> {
                binding.tvCategoryListName.text = context?.getString(R.string.category_upcoming)
            }
        }
    }

    companion object {
        private const val COLUMN = 2
        fun newInstance(): Fragment = FragmentMoviesList()
    }
}
