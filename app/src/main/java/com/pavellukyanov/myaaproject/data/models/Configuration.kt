package com.pavellukyanov.myaaproject.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

class Configuration(
    @SerialName("images")
    val images: Images,
)

@Serializable
class Images(

    @SerialName("poster_sizes")
    val posterSizes: List<String>,

    @SerialName("secure_base_url")
    val secureBaseUrl: String,
)