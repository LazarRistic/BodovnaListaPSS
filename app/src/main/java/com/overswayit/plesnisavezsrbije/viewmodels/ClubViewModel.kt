package com.overswayit.plesnisavezsrbije.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.viewModelScope
import com.overswayit.plesnisavezsrbije.models.Club
import com.overswayit.plesnisavezsrbije.repository.ClubsRepository
import kotlinx.coroutines.launch

/**
 * Created by lazarristic on 23/04/2019.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
class ClubViewModel(id: Int, application: Application) : AndroidViewModel(application) {
    private val clubsRepository = ClubsRepository(application)
    private val observableClub = MediatorLiveData<Club>()

    init {
        viewModelScope.launch {
            observableClub.addSource(clubsRepository.findById(id), observableClub::setValue)
        }
    }

    val club: LiveData<Club>
        get() = observableClub
}
