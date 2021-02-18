package com.pavellukyanov.myaaproject.data.models

import com.google.gson.annotations.SerializedName
import com.pavellukyanov.myaaproject.utils.Adult

class MovieDetails (
    @SerializedName("adult") var adult : Boolean,
    @SerializedName("backdrop_path") var backdropPath : String,
    @SerializedName("belongs_to_collection") var belongsToCollection : BelongsToCollection,
    @SerializedName("budget") var budget : Int,
    @SerializedName("genres") var genres : List<Genres>,
    @SerializedName("homepage") var homepage : String,
    @SerializedName("id") var id : Int,
    @SerializedName("imdb_id") var imdbId : String,
    @SerializedName("original_language") var originalLanguage : String,
    @SerializedName("original_title") var originalTitle : String,
    @SerializedName("overview") var overview : String,
    @SerializedName("popularity") var popularity : Double,
    @SerializedName("poster_path") var posterPath : String,
    @SerializedName("production_companies") var productionCompanies : List<ProductionCompanies>,
    @SerializedName("production_countries") var productionCountries : List<ProductionCountries>,
    @SerializedName("release_date") var releaseDate : String,
    @SerializedName("revenue") var revenue : Int,
    @SerializedName("runtime") var runtime : Int,
    @SerializedName("spoken_languages") var spokenLanguages : List<SpokenLanguages>,
    @SerializedName("status") var status : String,
    @SerializedName("tagline") var tagline : String,
    @SerializedName("title") var title : String,
    @SerializedName("video") var video : Boolean,
    @SerializedName("vote_average") var voteAverage : Int,
    @SerializedName("vote_count") var voteCount : Int
) {
    var finallyGenres = mutableListOf<String>()
    var moviePoster: String = ""
    val minimumAge: Int = if (adult) Adult.ADULT else Adult.NOT_ADULT
}

class BelongsToCollection (
    @SerializedName("id") var id : Int,
    @SerializedName("name") var name : String,
    @SerializedName("poster_path") var posterPath : String,
    @SerializedName("backdrop_path") var backdropPath : String
)

class ProductionCompanies (
    @SerializedName("id") var id : Int,
    @SerializedName("logo_path") var logoPath : String,
    @SerializedName("name") var name : String,
    @SerializedName("origin_country") var originCountry : String
)

class ProductionCountries (
    @SerializedName("iso_3166_1") var iso31661 : String,
    @SerializedName("name") var name : String
)

class SpokenLanguages (
    @SerializedName("english_name") var englishName : String,
    @SerializedName("iso_639_1") var iso6391 : String,
    @SerializedName("name") var name : String
)

class Credits (
    @SerializedName("id") var id : Int,
    @SerializedName("cast") var cast : List<Cast>,
    @SerializedName("crew") var crew : List<Crew>
)

class Cast (
    @SerializedName("adult") var adult : Boolean,
    @SerializedName("gender") var gender : Int,
    @SerializedName("id") var id : Int,
    @SerializedName("known_for_department") var knownForDepartment : String,
    @SerializedName("name") var name : String,
    @SerializedName("original_name") var originalName : String,
    @SerializedName("popularity") var popularity : Double,
    @SerializedName("profile_path") var profilePath : String,
    @SerializedName("cast_id") var castId : Int,
    @SerializedName("character") var character : String,
    @SerializedName("credit_id") var creditId : String,
    @SerializedName("order") var order : Int,
    var profilePoster: String = ""
)

class Crew (
    @SerializedName("adult") var adult : Boolean,
    @SerializedName("gender") var gender : Int,
    @SerializedName("id") var id : Int,
    @SerializedName("known_for_department") var knownForDepartment : String,
    @SerializedName("name") var name : String,
    @SerializedName("original_name") var originalName : String,
    @SerializedName("popularity") var popularity : Double,
    @SerializedName("profile_path") var profilePath : String,
    @SerializedName("credit_id") var creditId : String,
    @SerializedName("department") var department : String,
    @SerializedName("job") var job : String
)