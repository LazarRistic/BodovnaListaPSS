package com.overswayit.plesnisavezsrbije.viewmodels

import android.app.Application
import androidx.lifecycle.ViewModel
import com.overswayit.plesnisavezsrbije.models.DanceType
import com.overswayit.plesnisavezsrbije.networking.ListApiService
import com.overswayit.plesnisavezsrbije.repository.ListRepository
import com.overswayit.plesnisavezsrbije.utils.CoupleUtil
import kotlinx.coroutines.runBlocking

/**
 * Created by lazarristic on 2019-06-13.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
class CoupleRatingListViewModel(application: Application, private val coupleId: String) : ViewModel() {
    private val ratingListRepository = ListRepository(application, ListApiService.LIST_API)

    val title: String
        get() = runBlocking {
            CoupleUtil.getCoupleLastNames(ratingListRepository.getPointListCouple(coupleId, DanceType.LA).couple)
        }

    val pointsLatin: Int?
        get() = runBlocking {
            ratingListRepository.getRatingListCouple(coupleId, DanceType.LA).points
        }

    val pointsStandard: Int?
        get() = runBlocking {
            ratingListRepository.getRatingListCouple(coupleId, DanceType.ST).points
        }

    val pointsCombined: Int?
        get() = runBlocking {
            ratingListRepository.getRatingListCouple(coupleId, DanceType.KM).points
        }

    val placeLatin: Int?
        get() = runBlocking {
            ratingListRepository.getRatingListCouple(coupleId, DanceType.LA).place
        }

    val placeStandard: Int?
        get() = runBlocking {
            ratingListRepository.getRatingListCouple(coupleId, DanceType.ST).place
        }

    val placeCombined: Int?
        get() = runBlocking {
            ratingListRepository.getRatingListCouple(coupleId, DanceType.KM).place
        }
}