package com.overswayit.plesnisavezsrbije.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.google.gson.internal.LinkedTreeMap
import com.overswayit.plesnisavezsrbije.database.AppDatabase
import com.overswayit.plesnisavezsrbije.database.ClubDao
import com.overswayit.plesnisavezsrbije.models.Club
import com.overswayit.plesnisavezsrbije.networking.ClubsApiInterface
import com.overswayit.plesnisavezsrbije.parsers.server.ClubParser
import kotlinx.coroutines.Dispatchers


/**
 * Created by lazarristic on 19/02/2019.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
class ClubsRepository(application: Application, private val apiInterface: ClubsApiInterface) : BaseRepository() {
    private var clubDao: ClubDao = AppDatabase.invoke(application.applicationContext).clubDao()

    suspend fun getAll(ascending: Boolean = true): LiveData<List<Club>> = liveData(Dispatchers.IO) {
        if (ascending) {
            emitSource(clubDao.getAllByNameAscending())
        } else {
            emitSource(clubDao.getAllByNameDescending())
        }
    }

    suspend fun findById(id: String) = liveData(Dispatchers.IO) {
        emitSource(clubDao.findById(id))
    }

    suspend fun findByName(name: String) = liveData(Dispatchers.IO) {
        emit(clubDao.findByName(name))
    }

    suspend fun deleteAll() {
        clubDao.getAllForDeleting().forEach {
            clubDao.delete(it)
        }
    }

    suspend fun insertOrUpdate(clubs: List<Club>) {
        clubs.forEach {
            clubDao.insertOrUpdate(it)
        }
    }

    suspend fun getLatestClubs(): List<Club> {
        val result = safeApiCall(
                call = { apiInterface.getClubs() },
                error = "Error fetching clubs"
        )

        val clubs = ArrayList<Club>()

        if (result != null) {
            val list = result as java.util.ArrayList<*>

            list.forEach {
                @Suppress("UNCHECKED_CAST")
                clubs.add(ClubParser.toClubFromServerHashMap(it as LinkedTreeMap<String, Any>))
            }
        }

        return clubs
    }
}
