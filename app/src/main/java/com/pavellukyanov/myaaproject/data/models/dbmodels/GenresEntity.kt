package com.pavellukyanov.myaaproject.data.models.dbmodels

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity(tableName = "genres")
class GenresEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "name")
    var name: String
)

@Entity(tableName = "movie_genres")
class GenresWithMovieEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "genre_id")
    val genreId: Int,
    @ColumnInfo(name = "name")
    var name: String,
    @ColumnInfo(name = "movie_id")
    var movieId: Int
)

class MovieGenres(
    var id: Int,
    @Relation(parentColumn = "id", entityColumn = "movie_id")
    var actors: List<GenresWithMovieEntity>
)