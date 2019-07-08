package com.overswayit.plesnisavezsrbije.repository

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.overswayit.plesnisavezsrbije.database.AppDatabase
import com.overswayit.plesnisavezsrbije.database.ClubDao
import com.overswayit.plesnisavezsrbije.models.Club
import kotlinx.coroutines.Dispatchers


/**
 * Created by lazarristic on 19/02/2019.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
class ClubsRepository(application: Application) {
    private var clubDao: ClubDao = AppDatabase.invoke(application.applicationContext).clubDao()

    suspend fun getAll(ascending: Boolean = true): LiveData<List<Club>> = liveData(Dispatchers.IO) {
        if (ascending) {
            emitSource(clubDao.getAllByNameAscending())
        } else {
            emitSource(clubDao.getAllByNameDescending())
        }
    }

    suspend fun findById(id: Int) = liveData(Dispatchers.IO) {
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

    suspend fun insertOrUpdate(vararg clubs: Club) {
        clubDao.restartDB(clubs.asList(), clubs.asList())
    }
}
