package com.pavellukyanov.myaaproject.data.repository

import com.pavellukyanov.myaaproject.data.models.*

interface RepositoryInterface {
    suspend fun getPopularMovies(): List<Movie>

    suspend fun getAllGenres(): List<Genres>

    suspend fun getConfiguration(): Images

    suspend fun getNowPlaying(): List<Movie>

    suspend fun getMovieId(movieId: Int): Int
}