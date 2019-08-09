package com.overswayit.plesnisavezsrbije.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.overswayit.plesnisavezsrbije.models.DanceType
import com.overswayit.plesnisavezsrbije.models.PointListItem
import com.overswayit.plesnisavezsrbije.models.RatingListItem

/**
 * Created by lazarristic on 2019-07-04.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
@Dao
interface RatingListDao {
    @Query("SELECT * FROM rating_list_item")
    fun getAll(): LiveData<List<RatingListItem>>

    @Query("SELECT * FROM rating_list_item WHERE dance_type = :danceType AND (couple_name_male LIKE :query OR couple_name_female LIKE :query) ORDER BY CASE WHEN age_category = 'STS' THEN '1' WHEN age_category = 'SEN' THEN '2' WHEN age_category = 'STO' THEN '3' WHEN age_category = 'OML' THEN '4' WHEN age_category = 'MLO' THEN '5' WHEN age_category = 'PIO' THEN '6' ELSE age_category END ASC, points DESC")
    fun getCoupleByQuery(danceType: DanceType, query: String): LiveData<List<RatingListItem>>

    @Query("SELECT * FROM rating_list_item WHERE dance_type = :danceType")
    fun getAll(danceType: DanceType): LiveData<List<RatingListItem>>

    @Query("SELECT * FROM rating_list_item WHERE couple_id = :coupleId AND dance_type = :danceType")
    fun getCoupleById(coupleId: String, danceType: DanceType): RatingListItem

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdate(ratingListItem: RatingListItem)

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