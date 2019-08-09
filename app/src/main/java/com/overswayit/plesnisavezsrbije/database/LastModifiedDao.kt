package com.overswayit.plesnisavezsrbije.database

import androidx.room.*
import com.overswayit.plesnisavezsrbije.models.LastModified

/**
 * Created by lazarristic on 2019-08-08.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
@Dao
interface LastModifiedDao {
    @Query("SELECT date FROM LastModified WHERE name = :name")
    suspend fun getDateForList(name: String): String

    @Query("UPDATE LastModified SET date = :date WHERE name = :name ")
    suspend fun update(name: String, date: String)

    @Insert
    suspend fun insert(lastModified: LastModified)

    @Delete
    suspend fun delete(lastModified: LastModified)
}