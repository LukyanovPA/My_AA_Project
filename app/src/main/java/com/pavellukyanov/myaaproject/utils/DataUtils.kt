package com.pavellukyanov.myaaproject.utils

class DataUtils {
}

object ImageBaseUrl {
    var IMAGE_BASE_URL = ""
}

object GenresMap {
    val genres = mutableMapOf<Int, String>()
}

object PosterSizeList {
    var posterSizes: List<String>? = emptyList()
}

enum class PosterSizes(val size: String?) {
    W45(PosterSizeList.posterSizes?.get(0)),
    W92(PosterSizeList.posterSizes?.get(1)),
    W154(PosterSizeList.posterSizes?.get(2)),
    W185(PosterSizeList.posterSizes?.get(3)),
    W300(PosterSizeList.posterSizes?.get(4)),
    W500(PosterSizeList.posterSizes?.get(5)),
    ORIGINAL(PosterSizeList.posterSizes?.get(6))
}

object Adult{
    const val ADULT= 18
    const val NOT_ADULT= 0
}