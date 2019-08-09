package com.overswayit.plesnisavezsrbije.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.overswayit.plesnisavezsrbije.activities.PointListActivity
import com.overswayit.plesnisavezsrbije.models.*
import com.overswayit.plesnisavezsrbije.networking.ListApiService
import com.overswayit.plesnisavezsrbije.repository.FilterRepository
import com.overswayit.plesnisavezsrbije.repository.ListRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Created by lazarristic on 2019-07-31.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
abstract class ListViewModel2(application: Application, val danceType: DanceType) : AndroidViewModel(application) {
    private val listRepository: ListRepository = ListRepository(application, ListApiService.LIST_API)
    private val filtersRepository: FilterRepository = FilterRepository(application)
    private val couplesObservable = MediatorLiveData<List<PointListItem>>()
    private val filtersObservable = MediatorLiveData<List<Filter>>()
    private lateinit var couplesList: LiveData<List<PointListItem>>
    private lateinit var filtersList: LiveData<List<Filter>>
    private val searchQueryListener: PointListActivity.OnSearchQueryListener

    internal var query = ""
    private var filters = ArrayList<String>()

    init {
        searchQueryListener = object : PointListActivity.OnSearchQueryListener {
            override fun onQueryChanged(query: String) {
                setSearchQuery(query)
            }
        }

        viewModelScope.launch {
            filters = filtersRepository.getActiveFilters() as ArrayList<String>

            filtersList = filtersRepository.getActiveFiltersLiveData()

            filtersObservable.addSource(filtersList, filtersObservable::setValue)

            refreshList()
        }
    }

    open fun getQueryChangedListener(): PointListActivity.OnSearchQueryListener {
        return searchQueryListener
    }

    val listCouples: LiveData<List<PointListItem>> = couplesObservable

    val filtersLiveData: LiveData<List<Filter>> = filtersObservable

    fun setSearchQuery(query: String) {
        this@ListViewModel2.query = query

        couplesObservable.removeSource(couplesList)

        viewModelScope.launch {
            refreshList()
        }
    }

    fun updateFilters(filters: List<Filter>?) {
        this.filters = filtersToFilterNames(filters)

        viewModelScope.launch {
            refreshList()
        }
    }

    private suspend fun refreshList() {
        couplesList = Transformations.map(listRepository.getPointListCouplesWithQuery(danceType, query)) { pointListItem ->
            transformPointListItem(pointListItem)
        }

        couplesObservable.addSource(couplesList, couplesObservable::setValue)
    }

    private fun transformPointListItem(pointListItem: List<PointListItem>?): List<PointListItem> {
        val list = ArrayList<PointListItem>()

        pointListItem?.forEach {
            if (filters.contains(it.danceCategory.asString()) && filters.contains(it.ageCategory.asString())) {
                list.add(it)
            }
        }

        return list
    }

    private fun filtersToFilterNames(filters: List<Filter>?): ArrayList<String> {
        val filtersAsStringList = ArrayList<String>()

        filters?.forEach {
            if (it.active) {
                filtersAsStringList.add(it.filterName)
            }
        }

        return filtersAsStringList
    }

    fun updateFilter(filterName: String, active: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            filtersRepository.changeFilter(filterName, active)
        }
    }
}