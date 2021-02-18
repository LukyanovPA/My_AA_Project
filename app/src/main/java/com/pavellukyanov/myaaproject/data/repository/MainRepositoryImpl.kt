package com.pavellukyanov.myaaproject.data.repository

import android.util.Log
import com.pavellukyanov.myaaproject.data.api.MoviesRemoteRepo
import com.pavellukyanov.myaaproject.data.models.*
import com.pavellukyanov.myaaproject.utils.MovieCategory
import com.pavellukyanov.myaaproject.utils.PosterSizeList
import com.pavellukyanov.myaaproject.utils.PosterSizes
import kotlinx.coroutines.*
import java.util.*

class MainRepositoryImpl(private val moviesRemoteRepo: MoviesRemoteRepo) : RepositoryInterface {
    private var language: String = ""
    private lateinit var finallyMovieList: MutableList<Movie>
    private lateinit var finallyMovieDetails: MovieDetails
    private lateinit var finallyCredits: Credits
    private lateinit var config: Images
    private lateinit var genresMap: MutableMap<Int, String>

    override suspend fun getPopularMovies(): List<Movie> = moviesRemoteRepo.getPopularMovies(language).results

    override suspend fun getTopRatedMovies(): List<Movie> = moviesRemoteRepo.getTopRatedMovies(language).results

    override suspend fun getAllGenres(): List<Genres> = moviesRemoteRepo.getAllGenres(language).genres

    override suspend fun getConfiguration(): Images = moviesRemoteRepo.getConfiguration().images

    override suspend fun getNowPlaying(): List<Movie> = moviesRemoteRepo.getNowPlaying(language).results

    override suspend fun getUpcoming(): List<Movie> = moviesRemoteRepo.getUpcoming(language).results

    override suspend fun getMovieId(movieId: Int): MovieDetails = moviesRemoteRepo.getMovieId(movieId)

    override suspend fun getCredits(movieId: Int): Credits = moviesRemoteRepo.getCredits(movieId, language)

    override suspend fun getCreditsWithPosterUrl(movieId: Int): Credits {
        setLanguage()
            val tempCredits = getCredits(movieId)
            setupImageConfig()
            tempCredits.cast.forEach {
                it.profilePoster = "${config.secureBaseUrl}/${PosterSizes.W500.size}/${it.profilePath}"
            }
            tempCredits.crew.forEach {
                it.profilePath = "${config.secureBaseUrl}/${PosterSizes.W500.size}/${it.profilePath}"
            }
            finallyCredits = tempCredits
        return finallyCredits
    }

    override suspend fun getMovieDetailsWithGenres(movieId: Int): MovieDetails {
        setLanguage()
        val tempMovieDetails = getMovieId(movieId)
        Log.d("tttt", tempMovieDetails.overview)

        setupImageConfig()
        setupGenresMap()
        tempMovieDetails.moviePoster = "${config.secureBaseUrl}/${PosterSizes.W500.size}/${tempMovieDetails.posterPath}"
        tempMovieDetails.finallyGenres = mutableListOf()
        tempMovieDetails.genres.forEach {
            genresMap.get(it.id)?.let { it1 -> tempMovieDetails.finallyGenres.add(it1) }
        }
        finallyMovieDetails = tempMovieDetails
        Log.d("tttt", finallyMovieDetails.overview)
        return finallyMovieDetails
    }

    override suspend fun getMoviesWithGenres(methodName: MovieCategory): List<Movie> {
        setLanguage()
            finallyMovieList = mutableListOf()
            val movieList: MutableList<Movie> = mutableListOf()
            setupImageConfig()

            //get Genres list and put in map
            setupGenresMap()

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
        return finallyMovieList
    }

    private suspend fun setupImageConfig() {
            config = getConfiguration()
            PosterSizeList.posterSizes = config.posterSizes
    }

    private suspend fun setupGenresMap(): Map<Int, String> {
            genresMap = mutableMapOf()
            val genresTemp: List<Genres> = getAllGenres()
            genresTemp.forEach {
                genresMap.put(it.id, it.name)
            }
        return genresMap
    }

    private fun setLanguage() {
        val lang: String = Locale.getDefault().language
        if (lang == "ru") {
            language = RU
        } else {
            language = EN
        }
    }

    companion object {
        private const val LOG_TAG = "MainRepoImpl"
        private const val RU = "ru-RU"
        private const val EN = "en-US"
    }
}