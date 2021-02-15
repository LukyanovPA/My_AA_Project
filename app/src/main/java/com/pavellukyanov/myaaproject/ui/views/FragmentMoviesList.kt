package com.pavellukyanov.myaaproject.ui.views

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.pavellukyanov.myaaproject.R
import com.pavellukyanov.myaaproject.data.api.MoviesRemoteRepo
import com.pavellukyanov.myaaproject.data.api.Router
import com.pavellukyanov.myaaproject.data.models.Images
import com.pavellukyanov.myaaproject.data.models.Movie
import com.pavellukyanov.myaaproject.ui.adapters.ItemClickListener
import com.pavellukyanov.myaaproject.ui.adapters.MovieListAdapter
import com.pavellukyanov.myaaproject.databinding.FragmentMoviesListBinding
import com.pavellukyanov.myaaproject.ui.base.GenresViewModelFactory
import com.pavellukyanov.myaaproject.ui.base.MovieListViewModelFactory
import com.pavellukyanov.myaaproject.ui.viewmodels.GenresViewModel
import com.pavellukyanov.myaaproject.ui.viewmodels.MovieListViewModel
import com.pavellukyanov.myaaproject.utils.PosterSizeList
import com.pavellukyanov.myaaproject.utils.PosterSizes

class FragmentMoviesList : Fragment(R.layout.fragment_movies_list) {
    private lateinit var movieListViewModel: MovieListViewModel
    private lateinit var genresViewModel: GenresViewModel
    private lateinit var binding: FragmentMoviesListBinding
    private lateinit var resultList: MutableList<Movie>
    private lateinit var finishGenresMap: MutableMap<Int, String>
    private val config: Images by lazy {
        requireArguments().get(CONFIG) as Images
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMoviesListBinding.bind(view)
        resultList = mutableListOf()
        finishGenresMap = mutableMapOf()
        setPosterSize()
        initViewModel()
        subscribeGenres()
    }

    private fun subscribeGenres() {
        genresViewModel.getGenres().observe(this.viewLifecycleOwner, { genres ->
            genres?.let {
                finishGenresMap = genres
                subscribeOnMovies(finishGenresMap)
            }
        })
    }

    private fun setPosterSize() {
        PosterSizeList.posterSizes = config.posterSizes
    }

    private fun subscribeOnMovies(genresMap: Map<Int, String>) {
        movieListViewModel.getMovie().observe(this.viewLifecycleOwner, { response ->
            response?.let {
                Log.d("GenResp", "Response in Fragment - ${response.size}")
                    setImagesUrlAndGenres(genresMap, response)
            }
        })
    }

    private fun setImagesUrlAndGenres(genresMap: Map<Int, String>, movieList: List<Movie>) {
        for (movie in movieList) {
            val mov: Movie = movie
            mov.genres = mutableListOf()
            mov.moviePoster = "${config.secureBaseUrl}/${PosterSizes.W500.size}/${movie.posterPath}"
            mov.genreIds.forEach {
                val genreName = genresMap.get(it)
                if (genreName != null) {
                    Log.d("GenResp", "Мапа - $genreName")
                    mov.genres.add(genreName)
                }
            }
            Log.d("GenResp", "Колво - ${mov.genres.size}")
            resultList.add(mov)
        }
        initRecycler(resultList)
    }

    private fun initViewModel() {
        val factory = MovieListViewModelFactory(MoviesRemoteRepo(Router().movieApi))
        movieListViewModel = ViewModelProvider(this, factory).get(MovieListViewModel::class.java)
        val genresFactory = GenresViewModelFactory(MoviesRemoteRepo(Router().movieApi))
        genresViewModel = ViewModelProvider(this, genresFactory).get(GenresViewModel::class.java)
    }

    private fun initRecycler(movies: List<Movie>) {
        val adapter = MovieListAdapter(movies, clickListener)
        binding.recViewMovieList.adapter = adapter
        binding.recViewMovieList.layoutManager = GridLayoutManager(context, 2)
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
        fun newInstance(config: Images): Fragment = FragmentMoviesList().apply {
            arguments = bundleOf(CONFIG to config)
        }

        const val CONFIG = "config"
    }
}