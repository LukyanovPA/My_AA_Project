package com.pavellukyanov.myaaproject.data.repository

import android.content.Context
import com.pavellukyanov.myaaproject.data.models.dbmodels.GenresEntity
import com.pavellukyanov.myaaproject.data.models.dbmodels.GenresWithMovieEntity
import com.pavellukyanov.myaaproject.data.models.networkmodels.Genres
import com.pavellukyanov.myaaproject.data.models.networkmodels.Movie

interface DataBaseRepoInterface {

    suspend fun setAllGenresInDataBase(genresList: List<Genres>)

    suspend fun setGenresMovie(movie: Movie)

    suspend fun getGenresFromMovieId(movieId: Int): List<GenresWithMovieEntity>

    suspend fun getAllGenresInDataBase(): List<GenresEntity>

    suspend fun getGenreInDataBase(id: Int): GenresEntity

    suspend fun testGh(id: Int)
}