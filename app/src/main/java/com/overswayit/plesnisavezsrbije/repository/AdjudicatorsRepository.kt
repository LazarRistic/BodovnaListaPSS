package com.overswayit.plesnisavezsrbije.repository

import android.app.Application
import android.os.Handler
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.overswayit.plesnisavezsrbije.database.AppDatabase
import com.overswayit.plesnisavezsrbije.models.Adjudicator
import com.overswayit.plesnisavezsrbije.models.AdjudicatorLicensesType
import com.overswayit.plesnisavezsrbije.models.AdjudicatorLicensesType.LA_ST
import com.overswayit.plesnisavezsrbije.models.AdjudicatorLicensesType.MODERN
import com.overswayit.plesnisavezsrbije.models.Club
import java.util.*
import java.util.stream.Collectors

/**
 * Created by lazarristic on 21/02/2019.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
class AdjudicatorsRepository(application: Application) {
    private val adjudicatorDao = AppDatabase.invoke(application.applicationContext).adjudicatorDao()

    suspend fun getAll(): LiveData<List<Adjudicator>> = liveData {
        emitSource(adjudicatorDao.getAll())
    }

    suspend fun getAllWithLicensesType(licensesType: AdjudicatorLicensesType): LiveData<List<Adjudicator>> = liveData {
        emitSource(adjudicatorDao.getAllWithLicensesType(licensesType))
    }

    suspend fun insertOrUpdate(adjudicators: List<Adjudicator>) {
        adjudicatorDao.deleteAllAndInsertAll(adjudicators, adjudicators)
    }
}
