package com.pavellukyanov.myaaproject.data.models.dbmodels

import androidx.room.TypeConverter

class Converters {

    @TypeConverter
    fun toListOfStrings(flatStringList: String): List<String> {
        return flatStringList.split(",")
    }

    @TypeConverter
    fun fromListOfStrings(listOfStrings: List<String>): String {
        return listOfStrings.joinToString(",")
    }
}