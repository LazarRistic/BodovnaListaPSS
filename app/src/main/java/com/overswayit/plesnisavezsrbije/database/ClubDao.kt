package com.overswayit.plesnisavezsrbije.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.overswayit.plesnisavezsrbije.models.Club

/**
 * Created by lazarristic on 2019-06-25.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
@Dao
interface ClubDao {
    @Query("SELECT * FROM club")
    fun getAll(): LiveData<List<Club>>

    @Query("SELECT * FROM club")
    fun getAllForDeleting(): List<Club>

    @Query("SELECT * FROM club ORDER BY name ASC")
    fun getAllByNameAscending(): LiveData<List<Club>>

    @Query("SELECT * FROM club ORDER BY name DESC")
    fun getAllByNameDescending(): LiveData<List<Club>>

    @Query("SELECT * FROM club WHERE id LIKE :id")
    fun findById(id: String): LiveData<Club>

    @Query("SELECT * FROM club WHERE name LIKE :name")
    fun findByName(name: String): LiveData<Club>

    @Insert
    suspend fun insertAll(vararg clubs: Club)

    @Delete
    suspend fun delete(club: Club)

    @Update
    suspend fun updateClub(vararg club: Club)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdate(club: Club)

    @Transaction
    suspend fun restartDB(oldClub: List<Club>, newClub: List<Club>) {
        oldClub.forEach {
            delete(it)
        }

        newClub.forEach {
            insertAll(it)
        }
    }
}