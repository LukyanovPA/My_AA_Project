package com.pavellukyanov.myaaproject.data.repository

import com.pavellukyanov.myaaproject.data.api.MoviesRemoteRepo
import com.pavellukyanov.myaaproject.data.models.*

class MainRepositoryImpl(private val moviesRemoteRepo: MoviesRemoteRepo) : RepositoryInterface {
    override suspend fun getPopularMovies(): List<Movie> = moviesRemoteRepo.getPopularMovies().results

    override suspend fun getAllGenres(): List<Genres> = moviesRemoteRepo.getAllGenres().genres

    override suspend fun getConfiguration(): Images = moviesRemoteRepo.getConfiguration().images

    override suspend fun getNowPlaying(): List<Movie> = moviesRemoteRepo.getNowPlaying().results

    override suspend fun getMovieId(movieId: Int): Int = moviesRemoteRepo.getMovieId(movieId).runtime
}