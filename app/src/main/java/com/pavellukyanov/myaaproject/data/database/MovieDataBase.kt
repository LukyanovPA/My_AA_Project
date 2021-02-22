package com.pavellukyanov.myaaproject.data.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.pavellukyanov.myaaproject.data.app.App
import com.pavellukyanov.myaaproject.data.models.dbmodels.*

@Database(entities = [CategoryEntity::class, GenresEntity::class, MovieEntity::class, Cast::class, Crew::class, GenresWithMovieEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class MovieDataBase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
    abstract fun castDao(): CastDao
    abstract fun crewDao(): CrewDao
    abstract fun categoryDao(): CategoryDao
    abstract fun genresDao(): GenresDao
    abstract fun genresWithMovieDao(): GenresWithMovieDao

    companion object {
        private const val DATABASE_NAME = "Movies.db"

        val instance: MovieDataBase by lazy {
            Room.databaseBuilder(
                App.getApp().applicationContext,
                MovieDataBase::class.java,
                DATABASE_NAME
            )
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}