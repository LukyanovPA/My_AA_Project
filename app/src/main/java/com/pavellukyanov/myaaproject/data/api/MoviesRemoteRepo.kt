package com.pavellukyanov.myaaproject.data.api

class MoviesRemoteRepo(private val movieAPI: MovieAPI) {
    suspend fun getPopularMovies() = movieAPI.getPopularMovies()

    suspend fun getTopRatedMovies() = movieAPI.getTopRatedMovies()

    suspend fun getAllGenres() = movieAPI.getAllGenres()

    suspend fun getConfiguration() = movieAPI.getConfiguration()

    suspend fun getNowPlaying() = movieAPI.getNowPlaying()

    suspend fun getUpcoming() = movieAPI.getUpcoming()

    suspend fun getMovieId(movieId: Int) = movieAPI.getMovieId(movieId)
}