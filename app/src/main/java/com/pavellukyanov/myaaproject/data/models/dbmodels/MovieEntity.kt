package com.pavellukyanov.myaaproject.data.models.dbmodels

import androidx.room.*

@Entity(tableName = "movie")
class MovieEntity(
    @PrimaryKey
    @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "adult") var adult: Int,
    @ColumnInfo(name = "genres") var genres: String,
    @ColumnInfo(name = "original_title") var originalTitle: String,
    @ColumnInfo(name = "overview") var overview: String,
    @ColumnInfo(name = "movie_poster") var moviePoster: String,
    @ColumnInfo(name = "release_date") var releaseDate: String,
    @ColumnInfo(name = "title") var title: String,
    @ColumnInfo(name = "vote_average") var voteAverage: Long,
    @ColumnInfo(name = "vote_count") var voteCount: Int,
)

@Entity(tableName = "cast")
class Cast(
    @PrimaryKey(autoGenerate = true)
    var id: Long,
    @ColumnInfo(name = "id_actor") var idActor: Int,
    @ColumnInfo(name = "known_for_department") var knownForDepartment: String,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "original_name") var originalName: String,
    @ColumnInfo(name = "profile_poster") var profilePoster: String,
    @ColumnInfo(name = "character") var character: String,
    @ColumnInfo(name = "movie_id") var movieId: Int
)

@Entity(tableName = "crew")
class Crew(
    @PrimaryKey(autoGenerate = true)
    var id: Long,
    @ColumnInfo(name = "id_crew") var idCrew: Int,
    @ColumnInfo(name = "known_for_department") var knownForDepartment: String,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "original_name") var originalName: String,
    @ColumnInfo(name = "profile_poster") var profilePoster: String,
    @ColumnInfo(name = "job") var job: String,
    @ColumnInfo(name = "movie_id") var movieId: Int
)

class MovieWithCreditsInDataBase(
    var id: Int,
    @Relation(parentColumn = "id", entityColumn = "movie_id")
    var actors: List<Cast>,
    @Relation(parentColumn = "id", entityColumn = "movie_id")
    var crews: List<Crew>
)