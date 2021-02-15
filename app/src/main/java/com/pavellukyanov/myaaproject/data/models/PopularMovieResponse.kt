package com.pavellukyanov.myaaproject.data.models

import com.google.gson.annotations.SerializedName
import com.pavellukyanov.myaaproject.utils.Adult
import com.pavellukyanov.myaaproject.utils.PosterSizes

class PopularMovieResponse (
   @SerializedName("page") var page : Int,
   @SerializedName("results") var results : List<Movie>,
   @SerializedName("total_pages") var totalPages : Int,
   @SerializedName("total_results") var totalResults : Int
)

class NowPlayingResponse (
   @SerializedName("dates") var dates : Dates,
   @SerializedName("page") var page : Int,
   @SerializedName("results") var results : List<Movie>,
   @SerializedName("total_pages") var totalPages : Int,
   @SerializedName("total_results") var totalResults : Int
)

class Dates (
   @SerializedName("maximum") var maximum : String,
   @SerializedName("minimum") var minimum : String
)

class MovieId(
   @SerializedName("runtime") var runtime : Int
)

class Movie (
   @SerializedName("adult") var adult : Boolean,
   @SerializedName("backdrop_path") var backdropPath : String,
   @SerializedName("genre_ids") var genreIds : List<Int>,
   @SerializedName("id") var id : Int,
   @SerializedName("original_language") var originalLanguage : String,
   @SerializedName("original_title") var originalTitle : String,
   @SerializedName("overview") var overview : String,
   @SerializedName("popularity") var popularity : Double,
   @SerializedName("poster_path") var posterPath : String,
   @SerializedName("release_date") var releaseDate : String,
   @SerializedName("title") var title : String,
   @SerializedName("video") var video : Boolean,
   @SerializedName("vote_average") var voteAverage : Double = 0.0,
   @SerializedName("vote_count") var voteCount : Int
) {
   var moviePoster: String = ""
   val minimumAge: Int = if (adult) Adult.ADULT else Adult.NOT_ADULT
   var genres = mutableListOf<String>()
   var runtime: Int = 0
}