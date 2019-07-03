package com.overswayit.plesnisavezsrbije.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.viewModelScope
import com.overswayit.plesnisavezsrbije.database.FakeClubs
import com.overswayit.plesnisavezsrbije.models.Club
import com.overswayit.plesnisavezsrbije.repository.ClubsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * Created by lazarristic on 19/02/2019.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
class ClubsViewModel(application: Application) : AndroidViewModel(application) {
    private val clubsRepository: ClubsRepository = ClubsRepository(application)
    private var asscending = true
    private val mObservableClubs = MediatorLiveData<List<Club>>()
    private lateinit var sortedClubsAsc: LiveData<List<Club>>
    private lateinit var sortedClubsDesc: LiveData<List<Club>>

    init {
        viewModelScope.launch {
            insertClubs()
            sortedClubsAsc = clubsRepository.getAll(true)
            sortedClubsDesc = clubsRepository.getAll(false)

            if (asscending) {
                mObservableClubs.addSource(sortedClubsAsc, mObservableClubs::setValue)
            } else {
                mObservableClubs.addSource(sortedClubsDesc, mObservableClubs::setValue)
            }
        }
    }

    fun allClubs(): LiveData<List<Club>> {
        return mObservableClubs
    }

    fun doSortAscending() {
        if (!asscending) {
            asscending = true
            mObservableClubs.removeSource(sortedClubsDesc)
            mObservableClubs.addSource(sortedClubsAsc, mObservableClubs::setValue)
        }
    }

    fun doSortDescending() {
        if (asscending) {
            asscending = false
            mObservableClubs.removeSource(sortedClubsAsc)
            mObservableClubs.addSource(sortedClubsDesc, mObservableClubs::setValue)
        }
    }

    private suspend fun insertClubs() = withContext(Dispatchers.IO) {
        FakeClubs.getAllClubs().forEach {
            clubsRepository.insertOrUpdate(it)
        }
    }
}
