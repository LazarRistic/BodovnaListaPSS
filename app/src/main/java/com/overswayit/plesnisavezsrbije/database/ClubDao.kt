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
    suspend fun findById(id: Int): List<Club>

    @Query("SELECT * FROM club WHERE name LIKE :name")
    suspend fun findByName(name: String): List<Club>

    @Insert
    suspend fun insertAll(vararg clubs: Club)

    @Delete
    suspend fun delete(club: Club)

    @Update
    suspend fun updateClub(vararg club: Club)
}