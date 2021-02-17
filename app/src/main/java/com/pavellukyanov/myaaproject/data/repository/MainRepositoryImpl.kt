package com.pavellukyanov.myaaproject.data.repository

import android.util.Log
import com.pavellukyanov.myaaproject.data.api.MoviesRemoteRepo
import com.pavellukyanov.myaaproject.data.models.*
import com.pavellukyanov.myaaproject.utils.MovieCategory
import com.pavellukyanov.myaaproject.utils.PosterSizeList
import com.pavellukyanov.myaaproject.utils.PosterSizes
import kotlinx.coroutines.*

class MainRepositoryImpl(private val moviesRemoteRepo: MoviesRemoteRepo) : RepositoryInterface {
    private lateinit var finallyMovieList: MutableList<Movie>
    private val handler = CoroutineExceptionHandler(handler = { _, error ->
        Log.d(LOG_TAG, "${error.message}")
    })
    private val scope = CoroutineScope(
        SupervisorJob()
                + Dispatchers.IO
                + handler
    )

    override suspend fun getPopularMovies(): List<Movie> = scope.async { moviesRemoteRepo.getPopularMovies().results }.await()

    override suspend fun getTopRatedMovies(): List<Movie> = scope.async { moviesRemoteRepo.getTopRatedMovies().results }.await()

    override suspend fun getAllGenres(): List<Genres> = scope.async { moviesRemoteRepo.getAllGenres().genres }.await()

    override suspend fun getConfiguration(): Images = scope.async { moviesRemoteRepo.getConfiguration().images }.await()

    override suspend fun getNowPlaying(): List<Movie> = scope.async { moviesRemoteRepo.getNowPlaying().results }.await()

    override suspend fun getUpcoming(): List<Movie> = scope.async { moviesRemoteRepo.getUpcoming().results }.await()

    override suspend fun getMovieId(movieId: Int): Int = moviesRemoteRepo.getMovieId(movieId).runtime

    override suspend fun getMoviesWithGenres(methodName: MovieCategory): List<Movie> {
        val job = scope.launch {
            finallyMovieList = mutableListOf()
            val movieList: MutableList<Movie> = mutableListOf()
            val config: Images = getConfiguration()
            PosterSizeList.posterSizes = config.posterSizes

            //get Genres list and put in map
            val genresMap: MutableMap<Int, String> = mutableMapOf()
            val genresTemp: List<Genres> = getAllGenres()
            genresTemp.forEach {
                genresMap.put(it.id, it.name)
            }

            //get List<Movie> without Genres and PosterUrl
            when (methodName) {
                MovieCategory.POPULAR -> {
                    getPopularMovies().forEach {
                        movieList.add(it)
                    }
                }
                MovieCategory.TOP_RATED -> {
                    getTopRatedMovies().forEach {
                        movieList.add(it)
                    }
                }
                MovieCategory.NOW_PLAYING -> {
                    getNowPlaying().forEach {
                        movieList.add(it)
                    }
                }
                MovieCategory.UPCOMING -> {
                    getUpcoming().forEach {
                        movieList.add(it)
                    }
                }
            }

            //set Genres, PosterUrl
            val tempList: MutableList<Movie> = mutableListOf()
            movieList.forEach {
                it.moviePoster = "${config.secureBaseUrl}/${PosterSizes.W500.size}/${it.posterPath}"
                it.genres = mutableListOf()
                it.genreIds.forEach { id ->
                    genresMap.get(id)?.let { it1 -> it.genres.add(it1) }
                }
                tempList.add(it)
            }
            finallyMovieList.clear()
            finallyMovieList = tempList
        }
        job.join()
        return finallyMovieList
    }

    companion object {
        private const val LOG_TAG = "MainRepoImpl"
    }
}