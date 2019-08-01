package com.overswayit.plesnisavezsrbije.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.viewModelScope
import com.overswayit.plesnisavezsrbije.activities.PointListActivity
import com.overswayit.plesnisavezsrbije.database.fake.FakePointList
import com.overswayit.plesnisavezsrbije.database.fake.FakeRatingList
import com.overswayit.plesnisavezsrbije.models.DanceType
import com.overswayit.plesnisavezsrbije.models.PointListItem
import com.overswayit.plesnisavezsrbije.models.RatingListItem
import com.overswayit.plesnisavezsrbije.networking.PointListApiService
import com.overswayit.plesnisavezsrbije.repository.ListRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * Created by lazarristic on 2019-05-23.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
class ListViewModel(application: Application) : AndroidViewModel(application) {
    private val listRepository: ListRepository = ListRepository(application, PointListApiService.pointListApi)
    private val observableLaPointListMediator = MediatorLiveData<List<PointListItem>>()
    private val observableStPointListMediator = MediatorLiveData<List<PointListItem>>()
    private val observableLaRatingListMediator = MediatorLiveData<List<RatingListItem>>()
    private val observableStRatingListMediator = MediatorLiveData<List<RatingListItem>>()
    private val observableKmRatingListMediator = MediatorLiveData<List<RatingListItem>>()
    private lateinit var pointListLatino: LiveData<List<PointListItem>>
    private lateinit var pointListStandard: LiveData<List<PointListItem>>
    private val searchQueryListener: PointListActivity.OnSearchQueryListener

    private var query = ""

    init {
        searchQueryListener = object : PointListActivity.OnSearchQueryListener {
            override fun onQueryChanged(query: String) {
                setSearchQuery(query)
            }
        }

        viewModelScope.launch {
            refreshPointList()
            refreshRatingList()

            fetchPointList()
        }
    }

    val laPointListCouples: LiveData<List<PointListItem>>
        get() = observableLaPointListMediator

    val stPointListCouples: LiveData<List<PointListItem>>
        get() = observableStPointListMediator

    val laRatingListCouples: LiveData<List<RatingListItem>>
        get() = observableLaRatingListMediator

    val stRatingListCouples: LiveData<List<RatingListItem>>
        get() = observableStRatingListMediator

    val kmRatingListCouples: LiveData<List<RatingListItem>>
        get() = observableKmRatingListMediator

    fun getQueryChangedListener(): PointListActivity.OnSearchQueryListener {
        return searchQueryListener
    }

    private suspend fun insertPointList() {
        val pointListLa = FakePointList.getPointListByDanceType(DanceType.LA)
        val pointListSt = FakePointList.getPointListByDanceType(DanceType.ST)
        val ratingListLa = FakeRatingList.getRatingListByDanceType(DanceType.LA)
        val ratingListSt = FakeRatingList.getRatingListByDanceType(DanceType.ST)
        val ratingListKm = FakeRatingList.getRatingListByDanceType(DanceType.KM)

        listRepository.deleteAllAndInsertPointList(pointListLa, pointListLa)
        listRepository.deleteAllAndInsertPointList(pointListSt, pointListSt)
        listRepository.deleteAllAndInsertRatingList(ratingListLa, ratingListLa)
        listRepository.deleteAllAndInsertRatingList(ratingListSt, ratingListSt)
        listRepository.deleteAllAndInsertRatingList(ratingListKm, ratingListKm)
    }

    private suspend fun fetchPointList() = withContext(Dispatchers.IO) {
        listRepository.insertOrUpdate(listRepository.getLatestPointList())
    }

    private suspend fun refreshPointList() {
        pointListLatino = listRepository.getPointListCouplesWithQuery(DanceType.LA, query)
        observableLaPointListMediator.addSource(pointListLatino, observableLaPointListMediator::setValue)

        pointListStandard = listRepository.getPointListCouplesWithQuery(DanceType.ST, query)
        observableStPointListMediator.addSource(pointListStandard, observableStPointListMediator::setValue)
    }

    private suspend fun refreshRatingList() {
        val ratingListLatino = listRepository.getAllRatingListCouples(DanceType.LA)
        observableLaRatingListMediator.addSource(ratingListLatino, observableLaRatingListMediator::setValue)

        val ratingListStandard = listRepository.getAllRatingListCouples(DanceType.ST)
        observableStRatingListMediator.addSource(ratingListStandard, observableStRatingListMediator::setValue)

        val ratingListCombination = listRepository.getAllRatingListCouples(DanceType.KM)
        observableKmRatingListMediator.addSource(ratingListCombination, observableKmRatingListMediator::setValue)
    }

    fun setSearchQuery(query: String) {
        this@ListViewModel.query = query

        observableLaPointListMediator.removeSource(pointListLatino)
        observableStPointListMediator.removeSource(pointListStandard)

        viewModelScope.launch {
            refreshPointList()
        }
    }
}