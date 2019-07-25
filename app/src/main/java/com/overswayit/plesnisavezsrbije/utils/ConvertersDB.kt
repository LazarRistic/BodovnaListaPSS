package com.overswayit.plesnisavezsrbije.utils

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.overswayit.plesnisavezsrbije.MyApp
import com.overswayit.plesnisavezsrbije.R
import com.overswayit.plesnisavezsrbije.models.*


/**
 * Created by lazarristic on 2019-06-25.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
class ConvertersDB {
    @TypeConverter
    fun fromString(value: String): ArrayList<String> {
        val listType = object : TypeToken<ArrayList<String>>() {

        }.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromArrayList(list: ArrayList<String>): String {
        val gson = Gson()
        return gson.toJson(list)
    }

    @TypeConverter
    fun fromDanceCategory(value: DanceCategory): String {
        return value.toString()
    }

    @TypeConverter
    fun fromDanceType(value: DanceType): String {
        return value.toString()
    }

    @TypeConverter
    fun fromListType(value: ListType): String {
        return value.toString()
    }

    @TypeConverter
    fun fromAgeCategory(value: AgeCategory): String {
        return value.asString()
    }

    @TypeConverter
    fun fromAdjudicatorLicencesType(value: FederationDanceType): String {
        return value.asString()
    }

    @TypeConverter
    fun fromCompetitionsList(value: ArrayList<Competition>): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun fromDanceCategoryString(value: String): DanceCategory {
        return when (value.toLowerCase()) {
            "i" -> DanceCategory.I
            "a" -> DanceCategory.A
            "b" -> DanceCategory.B
            "c" -> DanceCategory.C
            "d" -> DanceCategory.D
            else -> DanceCategory.D
        }
    }

    @TypeConverter
    fun fromDanceTypeString(value: String): DanceType {
        return when (value) {
            MyApp.applicationContext().getString(R.string.la) -> DanceType.LA
            MyApp.applicationContext().getString(R.string.st) -> DanceType.ST
            MyApp.applicationContext().getString(R.string.km) -> DanceType.KM
            else -> DanceType.LA
        }
    }

    @TypeConverter
    fun fromListTypeString(value: String): ListType {
        return when (value.toLowerCase()) {
            "point" -> ListType.POINT
            "rating" -> ListType.RATING
            "favorite_couples" -> ListType.FAVORITE_COUPLES
            else -> ListType.POINT
        }
    }

    @TypeConverter
    fun fromAgeCategoryString(value: String): AgeCategory {
        return AgeCategory.fromString(value)!!
    }

    @TypeConverter
    fun fromAdjudicatorLicencesTypeString(value: String): FederationDanceType {
        return FederationDanceType.fromString(value)
    }

    @TypeConverter
    fun fromCompetitionsListString(value: String): ArrayList<Competition> {
        return Gson().fromJson(value, object : TypeToken<ArrayList<Competition>>() {}.type)
    }
}