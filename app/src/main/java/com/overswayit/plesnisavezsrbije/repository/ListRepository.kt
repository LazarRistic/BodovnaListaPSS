package com.overswayit.plesnisavezsrbije.repository

import android.app.Application
import android.text.TextUtils
import androidx.lifecycle.liveData
import com.google.gson.internal.LinkedTreeMap
import com.overswayit.plesnisavezsrbije.database.AppDatabase
import com.overswayit.plesnisavezsrbije.database.PointListDao
import com.overswayit.plesnisavezsrbije.database.RatingListDao
import com.overswayit.plesnisavezsrbije.models.CouplesListItem
import com.overswayit.plesnisavezsrbije.models.DanceType
import com.overswayit.plesnisavezsrbije.models.PointListItem
import com.overswayit.plesnisavezsrbije.models.RatingListItem
import com.overswayit.plesnisavezsrbije.networking.ListApiInterface
import com.overswayit.plesnisavezsrbije.parsers.server.PointListParser
import com.overswayit.plesnisavezsrbije.parsers.server.RatingListParser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Created by lazarristic on 2019-05-23.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
class ListRepository(private val application: Application, private val apiInterface: ListApiInterface) : BaseRepository() {
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

    suspend fun getRatingListCouplesWithQuery(danceType: DanceType, query: String?) = liveData(Dispatchers.IO) {
        val queryForDB =
                if (TextUtils.isEmpty(query)) {
                    "%"
                } else {
                    "%" + query + "%"
                }

        emitSource(ratingListDao.getCoupleByQuery(danceType, queryForDB))
    }

    suspend fun insertOrUpdate(coupleList: List<CouplesListItem>) {
        coupleList.forEach {
            when(it) {
                is PointListItem -> pointListDao.insertOrUpdate(it)
                is RatingListItem -> ratingListDao.insertOrUpdate(it)
            }
        }

    }

    suspend fun getPointListCouple(coupleId: String, danceType: DanceType): PointListItem = withContext(Dispatchers.IO) {
        pointListDao.getCoupleById(coupleId, danceType)
    }

    suspend fun getRatingListCouple(coupleId: String, danceType: DanceType): RatingListItem = withContext(Dispatchers.IO) {
        ratingListDao.getCoupleById(coupleId, danceType)
    }

    suspend fun getLatestPointList(): List<PointListItem> {
        val result = safeApiCall(
                call = { apiInterface.getPointList() },
                error = "Error fetching point list"
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

    suspend fun getLatestRatingList(): List<RatingListItem> {
        val result = safeApiCall(
                call = { apiInterface.getRatingList() },
                error = "Error fetching rating list"
        )

        val ratingList = ArrayList<RatingListItem>()

        if (result != null) {
            val list = result as java.util.ArrayList<*>

            list.forEach {
                @Suppress("UNCHECKED_CAST")
                ratingList.add(RatingListParser.toRatingListItemFromServerHashMap(it as LinkedTreeMap<String, Any>, DanceType.LA))
                ratingList.add(RatingListParser.toRatingListItemFromServerHashMap(it, DanceType.ST))
                ratingList.add(RatingListParser.toRatingListItemFromServerHashMap(it, DanceType.KM))
            }
        }

        return ratingList
    }
}

