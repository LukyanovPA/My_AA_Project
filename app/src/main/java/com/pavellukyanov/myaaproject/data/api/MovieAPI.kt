package com.pavellukyanov.myaaproject.data.api

import com.pavellukyanov.myaaproject.data.models.PopularMovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieAPI {
    @GET("movie/popular")
    suspend fun getPopularMovies(
//        @Query("language") language: String,
//        @Query("page") page: Int
    ): PopularMovieResponse
}