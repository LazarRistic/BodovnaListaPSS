package com.overswayit.plesnisavezsrbije.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.viewModelScope
import com.overswayit.plesnisavezsrbije.models.Club
import com.overswayit.plesnisavezsrbije.networking.ClubsApiService
import com.overswayit.plesnisavezsrbije.repository.ClubsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * Created by lazarristic on 19/02/2019.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
class ClubsViewModel(application: Application) : AndroidViewModel(application) {
    private val clubsRepository: ClubsRepository = ClubsRepository(application, ClubsApiService.clubsApi)
    private var ascending = true
    private val observableClubs = MediatorLiveData<List<Club>>()
    private lateinit var sortedClubsAsc: LiveData<List<Club>>
    private lateinit var sortedClubsDesc: LiveData<List<Club>>

    init {
        viewModelScope.launch {
            sortedClubsAsc = clubsRepository.getAll(true)
            sortedClubsDesc = clubsRepository.getAll(false)

            if (ascending) {
                observableClubs.addSource(sortedClubsAsc, observableClubs::setValue)
            } else {
                observableClubs.addSource(sortedClubsDesc, observableClubs::setValue)
            }

            fetchClubs()
        }
    }

    fun allClubs(): LiveData<List<Club>> {
        return observableClubs
    }

    fun doSortAscending() {
        if (!ascending) {
            ascending = true
            observableClubs.removeSource(sortedClubsDesc)
            observableClubs.addSource(sortedClubsAsc, observableClubs::setValue)
        }
    }

    fun doSortDescending() {
        if (ascending) {
            ascending = false
            observableClubs.removeSource(sortedClubsAsc)
            observableClubs.addSource(sortedClubsDesc, observableClubs::setValue)
        }
    }

    private suspend fun fetchClubs() = withContext(Dispatchers.IO) {
        clubsRepository.insertOrUpdate(clubsRepository.getLatestClubs())
    }
}
