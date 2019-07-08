package com.overswayit.plesnisavezsrbije.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.overswayit.plesnisavezsrbije.models.Adjudicator
import com.overswayit.plesnisavezsrbije.models.AdjudicatorLicensesType

/**
 * Created by lazarristic on 2019-07-08.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
@Dao
interface AdjudicatorDao {
    @Query("SELECT * FROM adjudicator")
    fun getAll(): LiveData<List<Adjudicator>>

    @Query("SELECT * FROM adjudicator WHERE licenses_type = :type")
    fun getAllWithLicensesType(type: AdjudicatorLicensesType): LiveData<List<Adjudicator>>

    @Insert
    suspend fun insertAll(adjudicators: List<Adjudicator>)

    @Delete
    suspend fun deleteAll(adjudicators: List<Adjudicator>)

    @Transaction
    suspend fun deleteAllAndInsertAll(oldAdjudicator: List<Adjudicator>, newAdjudicator: List<Adjudicator>) {
        deleteAll(oldAdjudicator)
        insertAll(newAdjudicator)
    }
}