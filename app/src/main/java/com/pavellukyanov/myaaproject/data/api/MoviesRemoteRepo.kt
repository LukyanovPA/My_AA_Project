package com.pavellukyanov.myaaproject.data.api

import java.util.*

class MoviesRemoteRepo(private val movieAPI: MovieAPI) {
suspend fun getPopularMovies(lang: String) = movieAPI.getPopularMovies(lang)

    suspend fun getTopRatedMovies(lang: String) = movieAPI.getTopRatedMovies(lang)

    suspend fun getAllGenres(lang: String) = movieAPI.getAllGenres(lang)

    suspend fun getConfiguration() = movieAPI.getConfiguration()

    suspend fun getNowPlaying(lang: String) = movieAPI.getNowPlaying(lang)

    suspend fun getUpcoming(lang: String) = movieAPI.getUpcoming(lang)

    suspend fun getMovieId(movieId: Int) = movieAPI.getMovieId(movieId)

    suspend fun getCredits(movieId: Int, lang: String) = movieAPI.getCredits(movieId, lang)
}