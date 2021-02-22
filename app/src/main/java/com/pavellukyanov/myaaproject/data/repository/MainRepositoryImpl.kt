package com.pavellukyanov.myaaproject.data.repository

import android.content.Context
import com.pavellukyanov.myaaproject.data.api.MoviesRemoteRepo
import com.pavellukyanov.myaaproject.data.database.MovieDataBase
import com.pavellukyanov.myaaproject.data.models.networkmodels.Credits
import com.pavellukyanov.myaaproject.data.models.networkmodels.Movie
import com.pavellukyanov.myaaproject.utils.MovieCategory

class MainRepositoryImpl(
    private val networkRepoInterface: NetworkRepoInterface,
    private val dataBaseRepoInterface: DataBaseRepoInterface
): MainRepositoryInterface {

    override suspend fun getMovie(methodName: MovieCategory): List<Movie> {
        TODO("Not yet implemented")
    }

    override suspend fun getCredits(movieId: Int): Credits {
        TODO("Not yet implemented")
    }

    override suspend fun testBaza(id: Int) {
        val listGenres = networkRepoInterface.getAllGenres()
        dataBaseRepoInterface.setAllGenresInDataBase(listGenres)
        dataBaseRepoInterface.testGh(id)
    }

    fun testDataBase() {

    }
}