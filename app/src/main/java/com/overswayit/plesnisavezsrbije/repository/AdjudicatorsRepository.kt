package com.overswayit.plesnisavezsrbije.repository

import android.app.Application
import android.os.Handler
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.google.gson.internal.LinkedTreeMap
import com.overswayit.plesnisavezsrbije.database.AppDatabase
import com.overswayit.plesnisavezsrbije.models.Adjudicator
import com.overswayit.plesnisavezsrbije.models.AdjudicatorLicensesType
import com.overswayit.plesnisavezsrbije.models.AdjudicatorLicensesType.LA_ST
import com.overswayit.plesnisavezsrbije.models.AdjudicatorLicensesType.MODERN
import com.overswayit.plesnisavezsrbije.models.Club
import com.overswayit.plesnisavezsrbije.networking.AdjudicatorsApiInterface
import com.overswayit.plesnisavezsrbije.networking.AdjudicatorsApiService
import com.overswayit.plesnisavezsrbije.parsers.server.AdjudicatorParser
import com.overswayit.plesnisavezsrbije.parsers.server.ClubParser
import java.util.*
import java.util.stream.Collectors

/**
 * Created by lazarristic on 21/02/2019.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
class AdjudicatorsRepository(application: Application, private val apiInterface: AdjudicatorsApiInterface) : BaseRepository() {
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

    suspend fun getLatestAdjudicators(): List<Adjudicator> {
        val result = safeApiCall(
                call = { apiInterface.getAdjudicators() },
                error = "Error fetching clubs"
        )

        val list = result!! as java.util.ArrayList<*>

        val adjudicators = ArrayList<Adjudicator>()

        list.forEach {
            @Suppress("UNCHECKED_CAST")
            adjudicators.add(AdjudicatorParser.toAdjudicatorFromServerHashMap(it as LinkedTreeMap<String, Any>))
        }

        return adjudicators
    }
}
