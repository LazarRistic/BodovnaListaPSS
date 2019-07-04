package com.overswayit.plesnisavezsrbije.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.overswayit.plesnisavezsrbije.models.DanceType
import com.overswayit.plesnisavezsrbije.models.RatingListItem

/**
 * Created by lazarristic on 2019-07-04.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
@Dao
interface RatingListDao {
    @Query("SELECT * FROM rating_list_item")
    fun getAll(): LiveData<List<RatingListItem>>

    @Query("SELECT * FROM rating_list_item WHERE dance_type = :danceType")
    fun getAll(danceType: DanceType): LiveData<List<RatingListItem>>

    @Insert
    suspend fun insert(vararg ratingListItem: RatingListItem)

    @Delete
    suspend fun delete(ratingListItem: RatingListItem)

    @Update
    suspend fun update(vararg ratingListItem: RatingListItem)

    @Transaction
    suspend fun deleteAndInsert(oldList: List<RatingListItem>, newList: List<RatingListItem>) {
        oldList.forEach {
            delete(it)
        }

        newList.forEach {
            insert(it)
        }
    }
}