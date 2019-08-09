package com.overswayit.plesnisavezsrbije.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.overswayit.plesnisavezsrbije.networking.LastModifiedApiService
import com.overswayit.plesnisavezsrbije.repository.LastModifiedRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking

/**
 * Created by lazarristic on 2019-08-08.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
class SplashViewModel(application: Application) : AndroidViewModel(application) {
    private val lastModifiedRepository = LastModifiedRepository(application, LastModifiedApiService.lastModifiedApi)

    fun shouldFetchPointList() = runBlocking(Dispatchers.IO) {
        val dbDate = lastModifiedRepository.getPointListLastModifiedDBDate()
        val webDate = lastModifiedRepository.fetchDate()

        if (dbDate == webDate) {
            return@runBlocking false
        } else {
            lastModifiedRepository.updatePointListDate(webDate as String)
            return@runBlocking true
        }
    }

    fun shouldFetchRatingList() = runBlocking(Dispatchers.IO) {
        val dbDate = lastModifiedRepository.getRatingListLastModifiedDBDate()
        val webDate = lastModifiedRepository.fetchDate()

        if (dbDate == webDate) {
            return@runBlocking false
        } else {
            lastModifiedRepository.updateRatingListDate(webDate as String)
            return@runBlocking true
        }
    }
}