package com.pavellukyanov.myaaproject.data.repository

import android.content.Context
import com.pavellukyanov.myaaproject.data.models.networkmodels.Credits
import com.pavellukyanov.myaaproject.data.models.networkmodels.Movie
import com.pavellukyanov.myaaproject.utils.MovieCategory

interface MainRepositoryInterface {
    suspend fun getMovie(methodName: MovieCategory): List<Movie>

    suspend fun getCredits(movieId: Int): Credits

    suspend fun testBaza(id: Int)
}