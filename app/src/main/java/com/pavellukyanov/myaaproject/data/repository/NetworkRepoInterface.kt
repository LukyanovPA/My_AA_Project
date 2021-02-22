package com.pavellukyanov.myaaproject.data.repository

import com.pavellukyanov.myaaproject.data.models.networkmodels.*
import com.pavellukyanov.myaaproject.utils.MovieCategory

interface NetworkRepoInterface {
    suspend fun getPopularMovies(): List<Movie>

    suspend fun getTopRatedMovies(): List<Movie>

    suspend fun getAllGenres(): List<Genres>

    suspend fun getConfiguration(): Images

    suspend fun getNowPlaying(): List<Movie>

    suspend fun getUpcoming(): List<Movie>

    suspend fun getMovieId(movieId: Int): MovieDetails

    suspend fun getCredits(movieId: Int): Credits

    suspend fun getCreditsWithPosterUrl(movieId: Int): Credits

    suspend fun getMovieDetailsWithGenres(movieId: Int): MovieDetails

    suspend fun getMoviesWithGenres(methodName: MovieCategory): List<Movie>
}