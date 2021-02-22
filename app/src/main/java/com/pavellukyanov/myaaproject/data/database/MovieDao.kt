package com.pavellukyanov.myaaproject.data.database

import androidx.room.*
import com.pavellukyanov.myaaproject.data.models.dbmodels.*

@Dao
interface MovieDao {
    //Movie
    @Query("SELECT * FROM movie WHERE id = :id")
    suspend fun getMovieId(id: Int): MovieEntity

    @Query("SELECT * FROM movie")
    suspend fun getAllMovies(): List<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: MovieEntity): Long

    @Update
    suspend fun updateMovie(movie: MovieEntity)

    @Query("DELETE FROM movie WHERE id = :id")
    suspend fun deleteMovie(id: Int)

    //test
    @Transaction
    @Query("SELECT id FROM movie")
    suspend fun getMoviesWithCredits(): List<MovieWithCreditsInDataBase>

    @Transaction
    @Query("SELECT id FROM movie")
    suspend fun getMovieGenresList(): List<MovieGenres>
}

@Dao
interface CastDao {
    //Cast
    @Query("SELECT * FROM `cast`")
    suspend fun getAllCasts(): List<Cast>

    @Query("SELECT * FROM `cast` WHERE id = :id")
    suspend fun getCast(id: Int): Cast

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCast(cast: Cast): Long

    @Update
    suspend fun updateCast(cast: Cast)

    @Query("DELETE FROM `cast` WHERE id = :id")
    suspend fun deleteCast(id: Int)
}

@Dao
interface CrewDao {
    //Crew
    @Query("SELECT * FROM `crew`")
    suspend fun getAllCrew(): List<Crew>

    @Query("SELECT * FROM `crew` WHERE id = :id")
    suspend fun getCrew(id: Int): Crew

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCrew(crew: Crew): Long

    @Update
    suspend fun updateCrew(crew: Crew)

    @Query("DELETE FROM crew WHERE id = :id")
    suspend fun deleteCrew(id: Int)

    @Query("DELETE FROM crew")
    suspend fun deleteAllCrews()
}

@Dao
interface CategoryDao {
    //Category
    @Query("SELECT * FROM category")
    suspend fun getAllCategory(): List<CategoryEntity>

    @Query("SELECT * FROM category WHERE name = :name")
    suspend fun getListMovieInCategory(name: String): CategoryEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategory(category: CategoryEntity): Long

    @Update
    suspend fun updateCategory(category: CategoryEntity)

    @Query("DELETE FROM category WHERE name = :name")
    suspend fun deleteCategory(name: String)
}

@Dao
interface GenresDao {

    //Genres
    @Query("SELECT * FROM genres")
    suspend fun getAllGenres(): List<GenresEntity>

    @Query("SELECT * FROM genres WHERE id = :id")
    suspend fun getGenre(id: Int): GenresEntity

    @Insert/*(onConflict = OnConflictStrategy.REPLACE)*/
    suspend fun insertGenre(genre: GenresEntity): Long

    @Update
    suspend fun updateGenre(genre: GenresEntity)

    @Query("DELETE FROM genres WHERE id = :id")
    suspend fun deleteGenre(id: Int)
}

@Dao
interface GenresWithMovieDao {
    //Genres with movie
    @Query("SELECT * FROM movie_genres")
    suspend fun getAllGenresWithMovie(): List<GenresWithMovieEntity>

    @Query("SELECT * FROM movie_genres WHERE movie_id = :movieId")
    suspend fun getGenreWithMovie(movieId: Int): List<GenresWithMovieEntity>

    @Insert/*(onConflict = OnConflictStrategy.REPLACE)*/
    suspend fun insertGenreWithMovie(genre: GenresWithMovieEntity): Long

    @Update
    suspend fun updateGenreWithMovie(genre: GenresWithMovieEntity)

    @Query("DELETE FROM movie_genres WHERE name = :name")
    suspend fun deleteGenre(name: String)

    @Query("DELETE FROM movie_genres")
    suspend fun deleteAllGenresWithMovie()
}