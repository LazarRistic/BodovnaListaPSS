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
class ClubsRepository(private val application: Application) {
    private var clubDao: ClubDao = AppDatabase.invoke(application.applicationContext).clubDao()

    suspend fun getAll(): LiveData<List<Club>> = liveData(Dispatchers.IO) {
        emitSource(clubDao.getAll())
    }

    suspend fun getAll(ascending: Boolean): LiveData<List<Club>> = liveData(Dispatchers.IO) {
        if (ascending) {
            emitSource(clubDao.getAllByNameAscending())
        } else {
            emitSource(clubDao.getAllByNameDescending())
        }
    }

    suspend fun findById(id: Int) = liveData(Dispatchers.IO) {
        emit(clubDao.findById(id))
    }

    suspend fun findByName(name: String) = liveData(Dispatchers.IO) {
        emit(clubDao.findByName(name))
    }

    suspend fun deleteAll() {
        Log.d("CLUBS REPO", "Start Deleting all")
        clubDao.getAllForDeleting().forEach {
            Log.d("CLUBS REPO", "Deleting ${it.name}")
            clubDao.delete(it)
        }
        Log.d("CLUBS REPO", "End Deleting all")
    }

    suspend fun insertOrUpdate(vararg clubs: Club) {
        Log.d("CLUBS REPO", "Start insertOrUpdate ${clubs.size} : clubs")
        clubs.forEach {
            if (clubDao.findById(it.id).isEmpty()) {
                Log.d("CLUBS REPO", "Start inserting ${it.name}")
                clubDao.insertAll(it)
            } else {
                Log.d("CLUBS REPO", "Start updating ${it.name}")
                clubDao.updateClub(it)
            }
        }

        Log.d("CLUBS REPO", "End insertOrUpdate")
    }
}
