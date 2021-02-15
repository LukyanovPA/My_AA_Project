package com.pavellukyanov.myaaproject.data.api

import com.pavellukyanov.myaaproject.data.models.*
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieAPI {
    @GET("movie/popular")
    suspend fun getPopularMovies(
//        @Query("language") language: String,
//        @Query("page") page: Int
    ): PopularMovieResponse

    @GET("movie/now_playing")
    suspend fun getNowPlaying(): NowPlayingResponse

    @GET("genre/movie/list")
    suspend fun getAllGenres(): GenreResponse

    @GET("configuration")
    suspend fun getConfiguration(): Configuration

    @GET("movie/{movie_id}")
    suspend fun getMovieId(
        @Path("{movie_id}") movieId: Int
    ): MovieId
}