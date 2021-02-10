package com.pavellukyanov.myaaproject.data.repository

import com.pavellukyanov.myaaproject.data.api.MoviesRemoteRepo
import com.pavellukyanov.myaaproject.data.models.PopularMovieResponse

class MainRepositoryImpl(private val moviesRemoteRepo: MoviesRemoteRepo) : RepositoryInterface {
    override suspend fun getPopularMovies(): PopularMovieResponse = moviesRemoteRepo.getPopularMovies()
}