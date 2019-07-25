package com.overswayit.plesnisavezsrbije.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.google.gson.internal.LinkedTreeMap
import com.overswayit.plesnisavezsrbije.database.AppDatabase
import com.overswayit.plesnisavezsrbije.database.CompetitionDao
import com.overswayit.plesnisavezsrbije.models.CompetitionEvent
import com.overswayit.plesnisavezsrbije.networking.CompetitionApiInterface
import com.overswayit.plesnisavezsrbije.parsers.server.CompetitionParser
import kotlinx.coroutines.Dispatchers

/**
 * Created by lazarristic on 2019-07-24.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
class CompetitionRepository(application: Application, private val apiInterface: CompetitionApiInterface) : BaseRepository() {
    private var competitionDao: CompetitionDao = AppDatabase.invoke(application.applicationContext).competitionDao()

    suspend fun getAll(): LiveData<List<CompetitionEvent>> = liveData(Dispatchers.IO) {
        emitSource(competitionDao.getAll())
    }

    suspend fun insertOrUpdate(competitionEvent: List<CompetitionEvent>) {
        competitionEvent.forEach {
            competitionDao.insertOrUpdate(it)
        }
    }

    suspend fun getLatestComps(): List<CompetitionEvent> {
        val result = safeApiCall(
                call = { apiInterface.getCompetitions() },
                error = "Error fetching competitions"
        )

        val competitionEvent = ArrayList<CompetitionEvent>()

        if (result != null) {
            val list = result as java.util.ArrayList<*>

            list.forEach {
                @Suppress("UNCHECKED_CAST")
                competitionEvent.add(CompetitionParser.toCompetitionEventFromServerServerHashMap(it as LinkedTreeMap<String, Any>))
            }
        }

        return competitionEvent
    }
}