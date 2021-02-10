package com.pavellukyanov.myaaproject.data.repository

import com.pavellukyanov.myaaproject.data.models.PopularMovieResponse

interface RepositoryInterface {
    suspend fun getPopularMovies(): PopularMovieResponse
}