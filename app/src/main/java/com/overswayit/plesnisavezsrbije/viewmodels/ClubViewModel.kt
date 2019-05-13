package com.overswayit.plesnisavezsrbije.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.overswayit.plesnisavezsrbije.models.Club
import com.overswayit.plesnisavezsrbije.repository.ClubRepository

/**
 * Created by lazarristic on 23/04/2019.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
class ClubViewModel(id: Int, application: Application) : AndroidViewModel(application) {
    private val clubRepository: ClubRepository = ClubRepository(id, application)

    val club: LiveData<Club>
        get() = clubRepository.clubLiveData
}
