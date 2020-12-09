package com.pavellukyanov.myaaproject.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pavellukyanov.myaaproject.R
import com.pavellukyanov.myaaproject.data.Actor
import com.pavellukyanov.myaaproject.data.Movie
import com.pavellukyanov.myaaproject.holders.ItemClickListener
import com.pavellukyanov.myaaproject.holders.MovieListAdapter
import kotlinx.android.synthetic.main.view_holder_movie.*

class FragmentMoviesList: Fragment() {
    private var rvMovies: RecyclerView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_movies_list, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val movieList = this.generateMovies()
        rvMovies = view.findViewById(R.id.recViewMovieList)
        val adapter = MovieListAdapter(view.context, movieList, clickListener)
        rvMovies?.adapter = adapter
        rvMovies?.layoutManager = GridLayoutManager(context, 2)
    }

    private val clickListener = object : ItemClickListener {
        override fun onItemClicked(movie: Movie) {
            showMoviesDetailsFragment(movie)
        }
    }

    private fun showMoviesDetailsFragment(movie: Movie) {
        val supportFragmentManager = activity?.supportFragmentManager
        supportFragmentManager?.beginTransaction()
            ?.replace(R.id.flFragmetn, FragmentMoviesDetails.newInstance(movie))
            ?.addToBackStack("FragmentMovieDetails")
            ?.commit()
    }

    fun generateMovies(): List<Movie> {
        return listOf(
            Movie(R.drawable.movie,
                "13+",
                "Action, Adventure, Fantasy",
                4.0F, "125 Reviews",
                "Avengers: End Game",
                "137 min",
                listOf(
                    Actor("Robert Downey Jr.", R.drawable.rbd),
                    Actor("Chris Evans", R.drawable.chrisevans),
                    Actor("Mark Ruffalo", R.drawable.markruffalo),
                    Actor("Chris Hemsworth", R.drawable.chrishem)
                )
            ),
            Movie(R.drawable.black_widow,
                "13+",
                "Action, Adventure, Fantasy",
                4.0F,
                "125 Reviews",
                "Black Widow",
                "137 min",
                listOf(
                    Actor("Robert Downey Jr.", R.drawable.rbd),
                    Actor("Chris Evans", R.drawable.chrisevans),
                    Actor("Mark Ruffalo", R.drawable.markruffalo),
                    Actor("Chris Hemsworth", R.drawable.chrishem)
                )
            ),
            Movie(R.drawable.tenet,
                "13+",
                "Action, Adventure, Fantasy",
                3.0F,
                "125 Reviews",
                "Tenet",
                "137 min",
                listOf(
                    Actor("Robert Downey Jr.", R.drawable.rbd),
                    Actor("Chris Evans", R.drawable.chrisevans),
                    Actor("Mark Ruffalo", R.drawable.markruffalo),
                    Actor("Chris Hemsworth", R.drawable.chrishem)
                )
            ),
            Movie(R.drawable.wonder_woman,
                "13+",
                "Action, Adventure, Fantasy",
                5.0F,
                "125 Reviews",
                "Avengers: End Game",
                "137 min",
                listOf(
                    Actor("Robert Downey Jr.", R.drawable.rbd),
                    Actor("Chris Evans", R.drawable.chrisevans),
                    Actor("Mark Ruffalo", R.drawable.markruffalo),
                    Actor("Chris Hemsworth", R.drawable.chrishem)
                )
            ),
        )
    }
}