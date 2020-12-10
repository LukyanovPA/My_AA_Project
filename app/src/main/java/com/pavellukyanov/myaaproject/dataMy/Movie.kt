package com.pavellukyanov.myaaproject.dataMy

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
class Movie(
    val movieImage: Int,
    val someID: String,
    val tag: String,
    val rating: Float,
    val reviews: String,
    val name: String,
    val movieTime: String,
    val actors: @RawValue List<Actor>?
) : Parcelable