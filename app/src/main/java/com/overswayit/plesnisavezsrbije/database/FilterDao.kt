package com.overswayit.plesnisavezsrbije.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.overswayit.plesnisavezsrbije.models.Filter

/**
 * Created by lazarristic on 2019-08-06.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
@Dao
interface FilterDao {
    @Query("SELECT filter_name FROM Filter WHERE active = 1")
    suspend fun getActiveFilters(): List<String>

    @Query("SELECT * FROM Filter")
    suspend fun getAll(): List<Filter>

    @Query("SELECT * FROM Filter WHERE active = 1")
    fun getActiveFiltersLiveData(): LiveData<List<Filter>>

    @Query("SELECT COUNT(*) FROM Filter WHERE active = 1")
    suspend fun getActiveCount(): Int

    @Query("SELECT * FROM Filter WHERE filter_name = :filterName")
    suspend fun findFilterByName(filterName: String): Filter

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdate(filter: Filter)

    @Insert
    suspend fun insert(filter: Filter)

    @Insert
    suspend fun insertAll(vararg filter: Filter)

    @Delete
    suspend fun delete(filter: Filter)

    @Query("UPDATE Filter SET active = :active WHERE filter_name = :filterName")
    suspend fun updateFilterStatus(filterName: String, active: Boolean)

    @Transaction
    suspend fun updateFilters(pairs: List<Pair<String, Boolean>>) {
        pairs.forEach {
            updateFilterStatus(it.first, it.second)
        }
    }
}