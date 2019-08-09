package com.overswayit.plesnisavezsrbije.repository

import android.app.Application
import com.google.gson.internal.LinkedTreeMap
import com.overswayit.plesnisavezsrbije.R
import com.overswayit.plesnisavezsrbije.database.AppDatabase
import com.overswayit.plesnisavezsrbije.models.Club
import com.overswayit.plesnisavezsrbije.models.LastModified
import com.overswayit.plesnisavezsrbije.networking.LastModifiedApiInterface
import com.overswayit.plesnisavezsrbije.parsers.server.ClubParser
import com.overswayit.plesnisavezsrbije.utils.StringUtil

/**
 * Created by lazarristic on 2019-08-08.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
class LastModifiedRepository(application: Application, val apiInterface: LastModifiedApiInterface): BaseRepository() {
    private val lastModifiedDao = AppDatabase.invoke(application.applicationContext).lastModifiedDao()

    suspend fun getPointListLastModifiedDBDate(): String {
        return lastModifiedDao.getDateForList(StringUtil.getString(R.string.point_list))
    }

    suspend fun getRatingListLastModifiedDBDate(): String {
        return lastModifiedDao.getDateForList(StringUtil.getString(R.string.rating_list))
    }

    suspend fun updatePointListDate(date: String) {
        return lastModifiedDao.update(StringUtil.getString(R.string.point_list), date)
    }

    suspend fun updateRatingListDate(date: String) {
        return lastModifiedDao.update(StringUtil.getString(R.string.rating_list), date)
    }

    suspend fun insert(name: String, date: String) {
        lastModifiedDao.insert(LastModified(name, date))
    }

    suspend fun delete(name: String, date: String) {
        lastModifiedDao.delete(LastModified(name, date))
    }

    suspend fun fetchDate(): String? {
        val result = safeApiCall(
                call = { apiInterface.getLastModifiedDate() },
                error = "Error fetching clubs"
        )

        var date: String? = null

        if (result != null) {
            @Suppress("UNCHECKED_CAST")
            val map: LinkedTreeMap<String, Any> = result as LinkedTreeMap<String, Any>

            date = map["date"] as String?
        }

        return date
    }
}