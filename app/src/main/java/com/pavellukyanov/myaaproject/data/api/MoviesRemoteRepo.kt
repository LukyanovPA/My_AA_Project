package com.pavellukyanov.myaaproject.data.api

class MoviesRemoteRepo(private val movieAPI: MovieAPI) {
    suspend fun getPopularMovies() = movieAPI.getPopularMovies()
}