package com.overswayit.plesnisavezsrbije.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.overswayit.plesnisavezsrbije.models.CompetitionEvent

/**
 * Created by lazarristic on 2019-07-24.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
@Dao
interface CompetitionDao {
    @Query("SELECT * FROM CompetitionEvent")
    fun getAll(): LiveData<List<CompetitionEvent>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdate(competitionEvent: CompetitionEvent)

    @Insert
    suspend fun insertAll(vararg competitionEvent: CompetitionEvent)

    @Delete
    suspend fun delete(competitionEvent: CompetitionEvent)
}