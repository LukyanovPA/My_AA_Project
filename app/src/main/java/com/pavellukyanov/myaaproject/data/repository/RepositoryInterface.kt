package com.pavellukyanov.myaaproject.data.repository

import com.pavellukyanov.myaaproject.data.models.*
import com.pavellukyanov.myaaproject.utils.MovieCategory

interface RepositoryInterface {
    suspend fun getPopularMovies(): List<Movie>

    suspend fun getTopRatedMovies(): List<Movie>

    suspend fun getAllGenres(): List<Genres>

    suspend fun getConfiguration(): Images

    suspend fun getNowPlaying(): List<Movie>

    suspend fun getUpcoming(): List<Movie>

    suspend fun getMovieId(movieId: Int): Int

    suspend fun getMoviesWithGenres(methodName: MovieCategory): List<Movie>
}