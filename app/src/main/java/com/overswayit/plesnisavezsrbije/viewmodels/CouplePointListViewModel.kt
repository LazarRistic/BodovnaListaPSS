package com.overswayit.plesnisavezsrbije.viewmodels

import android.app.Application
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import com.overswayit.plesnisavezsrbije.MyApp
import com.overswayit.plesnisavezsrbije.R
import com.overswayit.plesnisavezsrbije.models.DanceCategory
import com.overswayit.plesnisavezsrbije.models.DanceType
import com.overswayit.plesnisavezsrbije.networking.ListApiService
import com.overswayit.plesnisavezsrbije.repository.ListRepository
import com.overswayit.plesnisavezsrbije.utils.CoupleUtil
import kotlinx.coroutines.runBlocking

/**
 * Created by lazarristic on 2019-06-13.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
class CouplePointListViewModel(application: Application, private val coupleId: String) : ViewModel() {
    private val pointListRepository = ListRepository(application, ListApiService.LIST_API)

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

    val placeLatin: Int?
        get() = runBlocking {
            pointListRepository.getPointListCouple(coupleId, DanceType.LA).place
        }

    val placeStandard: Int?
        get() = runBlocking {
            pointListRepository.getPointListCouple(coupleId, DanceType.ST).place
        }

    val danceCategoryLatin: String?
        get() = runBlocking {
            danceCategoryToString(pointListRepository.getPointListCouple(coupleId, DanceType.LA).danceCategory)
        }

    val danceCategoryStandard: String?
        get() = runBlocking {
            danceCategoryToString(pointListRepository.getPointListCouple(coupleId, DanceType.ST).danceCategory)
        }

    val danceCategoryColorLatin: Int
        get() = runBlocking {
            ContextCompat.getColor(MyApp.applicationContext(), CoupleUtil.getDanceCategoryColor(pointListRepository.getPointListCouple(coupleId, DanceType.LA).danceCategory))
        }

    val danceCategoryColorStandard: Int
        get() = runBlocking {
            ContextCompat.getColor(MyApp.applicationContext(), CoupleUtil.getDanceCategoryColor(pointListRepository.getPointListCouple(coupleId, DanceType.ST).danceCategory))
        }

    private fun danceCategoryToString(danceCategory: DanceCategory?): String? {
        return when (danceCategory) {
            DanceCategory.A -> "A"
            DanceCategory.I -> "I"
            DanceCategory.B -> "B"
            DanceCategory.C -> "C"
            DanceCategory.D -> "D"
            else -> MyApp.applicationContext().getString(R.string.not_available)
        }
    }
}