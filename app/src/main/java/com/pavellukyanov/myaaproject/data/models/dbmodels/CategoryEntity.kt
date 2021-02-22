package com.pavellukyanov.myaaproject.data.models.dbmodels

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.NO_ACTION
import androidx.room.PrimaryKey

@Entity(
    tableName = "category"
//    foreignKeys = [ForeignKey(
//        entity = MovieEntity::class,
//        parentColumns = arrayOf("id"),
//        childColumns = arrayOf("list_movie"),
//        onDelete = NO_ACTION
//        )]
)
class CategoryEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long? = null,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "list_movie") var listMovie: String
)