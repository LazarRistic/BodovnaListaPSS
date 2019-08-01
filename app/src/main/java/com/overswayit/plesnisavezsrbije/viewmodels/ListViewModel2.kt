package com.overswayit.plesnisavezsrbije.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.viewModelScope
import com.overswayit.plesnisavezsrbije.activities.PointListActivity
import com.overswayit.plesnisavezsrbije.models.DanceType
import com.overswayit.plesnisavezsrbije.models.PointListItem
import com.overswayit.plesnisavezsrbije.networking.PointListApiService
import com.overswayit.plesnisavezsrbije.repository.ListRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * Created by lazarristic on 2019-07-31.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
abstract class ListViewModel2(application: Application) : AndroidViewModel(application) {
    internal val listRepository: ListRepository = ListRepository(application, PointListApiService.pointListApi)
    internal val observable = MediatorLiveData<List<PointListItem>>()
    protected lateinit var list: LiveData<List<PointListItem>>
    private val searchQueryListener: PointListActivity.OnSearchQueryListener

    internal var query = ""

    init {
        searchQueryListener = object : PointListActivity.OnSearchQueryListener {
            override fun onQueryChanged(query: String) {
                setSearchQuery(query)
            }
        }

        viewModelScope.launch {
            refreshList()
            fetchList()
        }
    }

    open fun getQueryChangedListener(): PointListActivity.OnSearchQueryListener {
        return searchQueryListener
    }

    val listCouples: LiveData<List<PointListItem>>
        get() = observable

    open suspend fun fetchList() = withContext(Dispatchers.IO) {
        listRepository.insertOrUpdate(listRepository.getLatestPointList())
    }

    open suspend fun refreshList() {
        list = listRepository.getPointListCouplesWithQuery(DanceType.LA, query)
        observable.addSource(list, observable::setValue)
    }

    fun setSearchQuery(query: String) {
        this@ListViewModel2.query = query

        observable.removeSource(list)

        viewModelScope.launch {
            refreshList()
        }
    }
}