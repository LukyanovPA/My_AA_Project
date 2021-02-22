package com.pavellukyanov.myaaproject.data.api

import com.pavellukyanov.myaaproject.data.models.networkmodels.*
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieAPI {
    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("language") language: String
//        @Query("page") page: Int
    ): PopularMovieAndTopRatedResponse

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(
        @Query("language") language: String
//        @Query("page") page: Int
    ): PopularMovieAndTopRatedResponse

    @GET("movie/now_playing")
    suspend fun getNowPlaying(
        @Query("language") language: String
    ): NowPlayingAndUpcomingResponse

    @GET("movie/upcoming")
    suspend fun getUpcoming(
        @Query("language") language: String
    ): NowPlayingAndUpcomingResponse

    @GET("genre/movie/list")
    suspend fun getAllGenres(
        @Query("language") language: String
    ): GenreResponse

    @GET("configuration")
    suspend fun getConfiguration(): Configuration

    @GET("movie/{movie_id}?")
    suspend fun getMovieId(
        @Path("movie_id") movieId: Int
    ): MovieDetails

    @GET("movie/{movie_id}/credits")
    suspend fun getCredits(
        @Path("movie_id") movieId: Int,
        @Query("language") language: String
    ): Credits
}