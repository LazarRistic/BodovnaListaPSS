package com.overswayit.plesnisavezsrbije.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.overswayit.plesnisavezsrbije.models.Club
import com.overswayit.plesnisavezsrbije.repository.ClubsRepository

/**
 * Created by lazarristic on 19/02/2019.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
class ClubsViewModel(application: Application) : AndroidViewModel(application) {
    private val clubsRepository: ClubsRepository = ClubsRepository(application)

    val allClubs: LiveData<List<Club>>
        get() = clubsRepository.clubsLiveData
}
