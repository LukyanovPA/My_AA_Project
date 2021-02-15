package com.pavellukyanov.myaaproject.data.models

import com.google.gson.annotations.SerializedName

class GenreResponse(
    @SerializedName("genres") val genres : List<Genres>
)

class Genres (
    @SerializedName("id") val id : Int,
    @SerializedName("name") val name : String
)