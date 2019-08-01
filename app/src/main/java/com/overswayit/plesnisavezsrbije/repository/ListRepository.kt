package com.overswayit.plesnisavezsrbije.repository

import android.app.Application
import android.text.TextUtils
import androidx.lifecycle.liveData
import com.google.gson.internal.LinkedTreeMap
import com.overswayit.plesnisavezsrbije.database.AppDatabase
import com.overswayit.plesnisavezsrbije.database.PointListDao
import com.overswayit.plesnisavezsrbije.database.RatingListDao
import com.overswayit.plesnisavezsrbije.models.DanceType
import com.overswayit.plesnisavezsrbije.models.PointListItem
import com.overswayit.plesnisavezsrbije.models.RatingListItem
import com.overswayit.plesnisavezsrbije.networking.PointListApiInterface
import com.overswayit.plesnisavezsrbije.parsers.server.PointListParser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Created by lazarristic on 2019-05-23.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
class ListRepository(private val application: Application, private val apiInterface: PointListApiInterface) : BaseRepository() {
    private var pointListDao: PointListDao = AppDatabase.invoke(application.applicationContext).pointListDao()
    private var ratingListDao: RatingListDao = AppDatabase.invoke(application.applicationContext).ratingListDao()

    suspend fun getAllPointListCouples(danceType: DanceType) = liveData(Dispatchers.IO) {
        emitSource(pointListDao.getAll(danceType))
    }

    suspend fun getAllRatingListCouples(danceType: DanceType) = liveData(Dispatchers.IO) {
        emitSource(ratingListDao.getAll(danceType))
    }

    suspend fun deleteAllAndInsertPointList(oldList: List<PointListItem>, newList: List<PointListItem>) = withContext(Dispatchers.IO) {
        pointListDao.deleteAndInsert(oldList, newList)
    }

    suspend fun deleteAllAndInsertRatingList(oldList: List<RatingListItem>, newList: List<RatingListItem>) = withContext(Dispatchers.IO) {
        ratingListDao.deleteAndInsert(oldList, newList)
    }

    suspend fun getPointListCouplesWithQuery(danceType: DanceType, query: String?) = liveData(Dispatchers.IO) {
        val queryForDB =
                if (TextUtils.isEmpty(query)) {
                    "%"
                } else {
                    "%" + query + "%"
                }

        emitSource(pointListDao.getCoupleByQuery(danceType, queryForDB))
    }

    suspend fun insertOrUpdate(pointList: List<PointListItem>) {
        pointList.forEach {
            pointListDao.insertOrUpdate(it)
        }
    }

    suspend fun getPointListCouple(coupleId: String, danceType: DanceType): PointListItem = withContext(Dispatchers.IO) {
        pointListDao.getCoupleById(coupleId, danceType)
    }

    suspend fun getLatestPointList(): List<PointListItem> {
        val result = safeApiCall(
                call = { apiInterface.getClubs() },
                error = "Error fetching clubs"
        )

        val pointList = ArrayList<PointListItem>()

        if (result != null) {
            val list = result as java.util.ArrayList<*>

            list.forEach {
                @Suppress("UNCHECKED_CAST")
                pointList.add(PointListParser.toPointListItemFromServerHashMap(it as LinkedTreeMap<String, Any>, DanceType.LA))
                pointList.add(PointListParser.toPointListItemFromServerHashMap(it, DanceType.ST))
            }
        }

        return pointList
    }
}

