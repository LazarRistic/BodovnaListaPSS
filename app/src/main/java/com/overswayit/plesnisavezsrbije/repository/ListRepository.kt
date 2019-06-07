package com.overswayit.plesnisavezsrbije.repository

import android.app.Application
import android.os.Handler
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.overswayit.plesnisavezsrbije.database.FakePointList
import com.overswayit.plesnisavezsrbije.database.FakeRatingList
import com.overswayit.plesnisavezsrbije.models.*
import java.util.ArrayList

/**
 * Created by lazarristic on 2019-05-23.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
class ListRepository(private val application: Application) {
    private val laPointListLiveData = MutableLiveData<List<PointListItem>>()
    private val stPointListLiveData = MutableLiveData<List<PointListItem>>()
    private val laRatingListLiveData = MutableLiveData<List<RatingListItem>>()
    private val stRatingListLiveData = MutableLiveData<List<RatingListItem>>()
    private val kmRatingListLiveData = MutableLiveData<List<RatingListItem>>()


    init {
        laPointListLiveData.postValue(FakePointList.getPointListByDanceType(DanceType.LA))
        stPointListLiveData.postValue(FakePointList.getPointListByDanceType(DanceType.ST))
        laRatingListLiveData.postValue(FakeRatingList.getRatingListByDanceType(DanceType.LA))
        stRatingListLiveData.postValue(FakeRatingList.getRatingListByDanceType(DanceType.ST))
        kmRatingListLiveData.postValue(FakeRatingList.getRatingListByDanceType(DanceType.KM))
    }

    fun getLaPointListCouples(): LiveData<List<PointListItem>> {
        return laPointListLiveData
    }

    fun getStPointListCouples(): LiveData<List<PointListItem>> {
        return stPointListLiveData
    }

    fun getLaRatingListCouples(): LiveData<List<RatingListItem>> {
        return laRatingListLiveData
    }

    fun getStRatingListCouples(): LiveData<List<RatingListItem>> {
        return stRatingListLiveData
    }

    fun getKmRatingListCouples(): LiveData<List<RatingListItem>> {
        return kmRatingListLiveData
    }
}

