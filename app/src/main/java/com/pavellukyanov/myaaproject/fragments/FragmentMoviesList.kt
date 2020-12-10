package com.pavellukyanov.myaaproject.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pavellukyanov.myaaproject.R
import com.pavellukyanov.myaaproject.dataMy.Movie
import com.pavellukyanov.myaaproject.adapters.ItemClickListener
import com.pavellukyanov.myaaproject.dataMy.MovieCallback
import com.pavellukyanov.myaaproject.adapters.MovieListAdapter
import com.pavellukyanov.myaaproject.data.loadMovies
import com.pavellukyanov.myaaproject.dataMy.DataSource
import kotlinx.android.synthetic.main.fragment_movies_list.*

class FragmentMoviesList: Fragment() {
    private var rvMovies: RecyclerView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_movies_list, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()
    }

    private fun initRecycler() {
        //эта переменная нужна для DiffUtil
        val movieList = generateMovies()
        val adapter = MovieListAdapter(clickListener)
        adapter.movies = loadMovies(context)
        recViewMovieList.adapter = adapter
        diffUtil(adapter, movieList)
        recViewMovieList.layoutManager = GridLayoutManager(context, 2)
    }

    //функция тестовая, когда будут приходить реальные данные - поменять!!
    private fun diffUtil(adapter: MovieListAdapter, movieList: List<Movie>) {
        val dif = MovieCallback(adapter.movies, movieList)
        val difResult: DiffUtil.DiffResult = DiffUtil.calculateDiff(dif)
        difResult.dispatchUpdatesTo(adapter)
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

    private fun generateMovies(): List<Movie> {
        return DataSource().generateMovies()
    }
}