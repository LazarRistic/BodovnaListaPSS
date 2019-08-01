package com.overswayit.plesnisavezsrbije.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.overswayit.plesnisavezsrbije.models.DanceCategory
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

    @Query("SELECT * FROM point_list_item WHERE dance_type = :danceType AND (couple_name_male LIKE :query OR couple_name_female LIKE :query) ORDER BY CASE WHEN age_category = 'STS' THEN '1' WHEN age_category = 'SEN' THEN '2' WHEN age_category = 'STO' THEN '3' WHEN age_category = 'OML' THEN '4' WHEN age_category = 'MLO' THEN '5' WHEN age_category = 'PIO' THEN '6' ELSE age_category END ASC, CASE WHEN dance_category = 'I' THEN '1' WHEN dance_category = 'A' THEN '2' WHEN dance_category = 'B' THEN '3' WHEN dance_category = 'C' THEN '4' WHEN dance_category = 'D' THEN '5' ELSE dance_category END ASC, points DESC")
    fun getCoupleByQuery(danceType: DanceType, query: String): LiveData<List<PointListItem>>

    @Query("SELECT * FROM point_list_item WHERE dance_type = :danceType ORDER BY CASE WHEN age_category = 'STS' THEN '1' WHEN age_category = 'SEN' THEN '2' WHEN age_category = 'STO' THEN '3' WHEN age_category = 'OML' THEN '4' WHEN age_category = 'MLO' THEN '5' WHEN age_category = 'PIO' THEN '6' ELSE age_category END ASC, CASE WHEN dance_category = 'I' THEN '1' WHEN dance_category = 'A' THEN '2' WHEN dance_category = 'B' THEN '3' WHEN dance_category = 'C' THEN '4' WHEN dance_category = 'D' THEN '5' ELSE dance_category END ASC, points DESC")
    fun getAll(danceType: DanceType): LiveData<List<PointListItem>>

    @Query("SELECT * FROM point_list_item WHERE couple_id = :coupleId AND dance_type = :danceType")
    fun getCoupleById(coupleId: String, danceType: DanceType): PointListItem

    @Insert
    suspend fun insert(vararg pointListItems: PointListItem)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdate(pointListItem: PointListItem)

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