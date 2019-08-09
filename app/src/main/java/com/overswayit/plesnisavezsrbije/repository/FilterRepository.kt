package com.overswayit.plesnisavezsrbije.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.overswayit.plesnisavezsrbije.database.AppDatabase
import com.overswayit.plesnisavezsrbije.database.FilterDao
import com.overswayit.plesnisavezsrbije.models.Filter
import kotlinx.coroutines.Dispatchers

/**
 * Created by lazarristic on 2019-08-06.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
class FilterRepository(application: Application) : BaseRepository() {
    private val filterDao: FilterDao = AppDatabase.invoke(application.applicationContext).filterDao()

    suspend fun getActiveCount(): Int {
        return filterDao.getActiveCount()
    }

    suspend fun getAll(): List<Filter> {
        return filterDao.getAll()
    }

    suspend fun getActiveFiltersLiveData(): LiveData<List<Filter>> = liveData(Dispatchers.IO) {
        emitSource(filterDao.getActiveFiltersLiveData())
    }

    suspend fun getActiveFilters(): List<String> {
        return  filterDao.getActiveFilters()
    }

    suspend fun changeFilters(pairs: List<Pair<String, Boolean>>) {
        filterDao.updateFilters(pairs)
    }

    suspend fun changeFilter(filterName: String, active: Boolean) {
        filterDao.updateFilterStatus(filterName, active)
    }

    private suspend fun findFilterByName(filterName: String): Filter {
        return filterDao.findFilterByName(filterName)
    }
}