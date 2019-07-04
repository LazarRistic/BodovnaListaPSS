package com.overswayit.plesnisavezsrbije.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.overswayit.plesnisavezsrbije.models.DanceType
import com.overswayit.plesnisavezsrbije.models.PointListItem

/**
 * Created by lazarristic on 2019-07-03.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
@Dao
interface PointListDao {
    @Query("SELECT * FROM point_list_item")
    fun getAll(): LiveData<List<PointListItem>>

    @Query("SELECT * FROM point_list_item WHERE dance_type = :danceType")
    fun getAll(danceType: DanceType): LiveData<List<PointListItem>>

    @Insert
    suspend fun insert(vararg pointListItems: PointListItem)

    @Delete
    suspend fun delete(pointListItem: PointListItem)

    @Update
    suspend fun update(vararg pointListItem: PointListItem)

    @Transaction
    suspend fun deleteAndInsert(oldList: List<PointListItem>, newList: List<PointListItem>) {
        oldList.forEach {
            delete(it)
        }

        newList.forEach {
            insert(it)
        }
    }
}