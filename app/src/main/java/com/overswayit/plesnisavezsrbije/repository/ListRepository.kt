package com.overswayit.plesnisavezsrbije.repository

import android.app.Application
import androidx.lifecycle.liveData
import com.overswayit.plesnisavezsrbije.database.AppDatabase
import com.overswayit.plesnisavezsrbije.database.PointListDao
import com.overswayit.plesnisavezsrbije.database.RatingListDao
import com.overswayit.plesnisavezsrbije.models.DanceType
import com.overswayit.plesnisavezsrbije.models.PointListItem
import com.overswayit.plesnisavezsrbije.models.RatingListItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Created by lazarristic on 2019-05-23.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
class ListRepository(private val application: Application) {
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
}

