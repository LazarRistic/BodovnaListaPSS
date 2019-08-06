package com.overswayit.plesnisavezsrbije.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.overswayit.plesnisavezsrbije.models.FavoriteCouple

/**
 * Created by lazarristic on 2019-08-02.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
@Dao
interface FavoriteCoupleDao {
    @Query("SELECT * FROM FavoriteCouple WHERE favorite = 1")
    fun getAll(): LiveData<List<FavoriteCouple>>

    @Query("SELECT * FROM FavoriteCouple WHERE couple_id = :coupleId")
    suspend fun findFavoriteCoupleByCoupleId(coupleId: String): FavoriteCouple

    @Query("SELECT favorite FROM FavoriteCouple WHERE couple_id = :coupleId")
    fun isFavorite(coupleId: String): LiveData<Boolean>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdate(favoriteCouple: FavoriteCouple)

    @Insert
    suspend fun insert(favoriteCouple: FavoriteCouple)

    @Delete
    suspend fun delete(favoriteCouple: FavoriteCouple)
}