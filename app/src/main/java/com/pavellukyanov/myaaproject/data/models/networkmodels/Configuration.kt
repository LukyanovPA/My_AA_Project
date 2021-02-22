package com.pavellukyanov.myaaproject.data.models.networkmodels

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

class Configuration(
    @SerializedName("images") var images : Images,
    @SerializedName("change_keys") var changeKeys : List<String>
)

@Parcelize
@Serializable
class Images(
    @SerializedName("base_url") var baseUrl : String,
    @SerializedName("secure_base_url") var secureBaseUrl : String,
    @SerializedName("backdrop_sizes") var backdropSizes : List<String>,
    @SerializedName("logo_sizes") var logoSizes : List<String>,
    @SerializedName("poster_sizes") var posterSizes : List<String>,
    @SerializedName("profile_sizes") var profileSizes : List<String>,
    @SerializedName("still_sizes") var stillSizes : List<String>
): Parcelable