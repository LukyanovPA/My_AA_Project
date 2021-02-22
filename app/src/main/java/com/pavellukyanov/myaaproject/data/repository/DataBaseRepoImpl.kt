package com.pavellukyanov.myaaproject.data.repository

import android.util.Log
import com.pavellukyanov.myaaproject.data.database.MovieDataBase
import com.pavellukyanov.myaaproject.data.models.dbmodels.GenresEntity
import com.pavellukyanov.myaaproject.data.models.dbmodels.GenresWithMovieEntity
import com.pavellukyanov.myaaproject.data.models.networkmodels.Genres
import com.pavellukyanov.myaaproject.data.models.networkmodels.Movie

class DataBaseRepoImpl : DataBaseRepoInterface {
    private val db = MovieDataBase.instance
    override suspend fun setAllGenresInDataBase(genresList: List<Genres>) {
        if (getAllGenresInDataBase().isEmpty()) {
            genresList.forEach {
                val genreEntity = GenresEntity(it.id, it.name)
                db.genresDao().insertGenre(genreEntity)
            }
        }
    }

    override suspend fun setGenresMovie(movie: Movie) {
        if (getGenresFromMovieId(movie.id).isEmpty()) {
            movie.genreIds.forEach {
                val genreWithMovie = GenresWithMovieEntity(it, getGenreInDataBase(it).name, movie.id)
                db.genresWithMovieDao().insertGenreWithMovie(genreWithMovie)
            }
        }
    }

    override suspend fun getGenresFromMovieId(movieId: Int): List<GenresWithMovieEntity> {
        return db.genresWithMovieDao().getGenreWithMovie(movieId)
    }

    override suspend fun getAllGenresInDataBase(): List<GenresEntity> {
        return db.genresDao().getAllGenres()
    }

    override suspend fun getGenreInDataBase(id: Int): GenresEntity {
        return db.genresDao().getGenre(id)
    }

    override suspend fun testGh(id: Int) {
        val db = getGenreInDataBase(id)
        Log.d("BaBab", "testGenre - $db")
    }
}