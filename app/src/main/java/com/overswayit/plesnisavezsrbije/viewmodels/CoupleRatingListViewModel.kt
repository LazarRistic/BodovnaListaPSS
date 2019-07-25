package com.overswayit.plesnisavezsrbije.viewmodels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.overswayit.plesnisavezsrbije.models.DanceType
import com.overswayit.plesnisavezsrbije.models.PointListItem
import com.overswayit.plesnisavezsrbije.models.RatingListItem
import com.overswayit.plesnisavezsrbije.networking.PointListApiService
import com.overswayit.plesnisavezsrbije.repository.ListRepository
import com.overswayit.plesnisavezsrbije.utils.CoupleUtil
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * Created by lazarristic on 2019-06-13.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
class CoupleRatingListViewModel(private val application: Application, private val coupleId: String) : ViewModel() {
    private val pointListRepository = ListRepository(application, PointListApiService.pointListApi)

    val title: String
        get() = runBlocking {
            CoupleUtil.getCoupleLastNames(pointListRepository.getPointListCouple(coupleId, DanceType.LA).couple)
        }

    val pointsLatin: Int?
        get() = runBlocking {
            pointListRepository.getPointListCouple(coupleId, DanceType.LA).points
        }

    val pointsStandard: Int?
        get() = runBlocking {
            pointListRepository.getPointListCouple(coupleId, DanceType.ST).points
        }

    val pointsCombined: Int?
        get() = runBlocking {
//            pointListRepository.getPointListCouple(coupleId, DanceType.KM).points
            pointListRepository.getPointListCouple(coupleId, DanceType.LA).points
        }

    val placeLatin: Int?
        get() = runBlocking {
            pointListRepository.getPointListCouple(coupleId, DanceType.LA).place
        }

    val placeStandard: Int?
        get() = runBlocking {
            pointListRepository.getPointListCouple(coupleId, DanceType.ST).place
        }

    val placeCombined: Int?
        get() = runBlocking {
//            pointListRepository.getPointListCouple(coupleId, DanceType.KM).place
            pointListRepository.getPointListCouple(coupleId, DanceType.LA).place
        }
}