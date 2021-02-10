package com.pavellukyanov.myaaproject.data.models

import com.google.gson.annotations.SerializedName
import com.pavellukyanov.myaaproject.utils.Adult
import com.pavellukyanov.myaaproject.utils.GenresMap
import com.pavellukyanov.myaaproject.utils.ImageBaseUrl
import com.pavellukyanov.myaaproject.utils.PosterSizes

class PopularMovieResponse (
   @SerializedName("page") var page : Int,
   @SerializedName("results") var results : List<Movie>,
   @SerializedName("total_pages") var totalPages : Int,
   @SerializedName("total_results") var totalResults : Int
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
   @SerializedName("vote_average") var voteAverage : Double,
   @SerializedName("vote_count") var voteCount : Int
) {
   var moviePoster: String = ImageBaseUrl.IMAGE_BASE_URL + PosterSizes.W500.size + posterPath
   val rating: Float = (voteAverage / 2).toFloat()
   val minimumAge: Int = if (adult) Adult.ADULT else Adult.NOT_ADULT
   val genre: () -> String = {
      val genres = mutableListOf<String>()
      genreIds?.forEach {
         genres.add(GenresMap.genres.getValue(it))
      }
      genres.joinToString()
   }
}