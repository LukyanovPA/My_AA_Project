package com.pavellukyanov.myaaproject.data

import android.graphics.drawable.Drawable

data class Movie(
    val movieImage: Int,
    val someID: String,
    val tag: String,
    val rating: Float,
    val reviews: String,
    val name: String,
    val movieTime: String,
    val actors: List<Actor>?
)