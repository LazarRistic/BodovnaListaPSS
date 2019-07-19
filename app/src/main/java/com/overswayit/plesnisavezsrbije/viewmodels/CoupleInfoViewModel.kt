package com.overswayit.plesnisavezsrbije.viewmodels

import android.app.Application
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.overswayit.plesnisavezsrbije.MyApp
import com.overswayit.plesnisavezsrbije.R
import com.overswayit.plesnisavezsrbije.models.*
import com.overswayit.plesnisavezsrbije.networking.PointListApiService
import com.overswayit.plesnisavezsrbije.repository.ListRepository
import com.overswayit.plesnisavezsrbije.utils.CoupleUtil
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * Created by lazarristic on 2019-06-14.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
class CoupleInfoViewModel(val application: Application, private val coupleId: String) : ViewModel() {
    private val pointListRepository = ListRepository(application, PointListApiService.pointListApi)

    val clubTown: String?
        get() = runBlocking {
            pointListRepository.getPointListCouple(coupleId, DanceType.LA).couple.club.town
        }

    val clubName: String?
        get() = runBlocking {
            pointListRepository.getPointListCouple(coupleId, DanceType.LA).couple.club.name
        }

    val coupleImageUrl: String?
        get() {
            return "https://i.ibb.co/RDzdjpg/FIL-2903.jpg"
        }

    val clubLogoUrl: String?
        get() = runBlocking {
            pointListRepository.getPointListCouple(coupleId, DanceType.LA).couple.club.logoUrl
        }

    val nameMale: String?
        get() = runBlocking {
            pointListRepository.getPointListCouple(coupleId, DanceType.LA).couple.nameMale
        }

    val nameFemale: String?
        get() = runBlocking {
            pointListRepository.getPointListCouple(coupleId, DanceType.LA).couple.nameFemale
        }

    val ageCategoryText: String?
        get() = runBlocking {
            pointListRepository.getPointListCouple(coupleId, DanceType.LA).ageCategory.asString()
        }

    val ageCategoryColor: Int
        get() = runBlocking {
            ContextCompat.getColor(MyApp.applicationContext(), CoupleUtil.getAgeCategoryColor(pointListRepository.getPointListCouple(coupleId, DanceType.LA).ageCategory))
        }

    val latinCategoryText: String?
        get() = runBlocking {
            pointListRepository.getPointListCouple(coupleId, DanceType.LA).danceCategory.asString()
        }

    val standardCategoryText: String?
        get() = runBlocking {
            pointListRepository.getPointListCouple(coupleId, DanceType.ST).danceCategory.asString()
        }

    val latinCategoryColor: Int
        get() = runBlocking {
            ContextCompat.getColor(MyApp.applicationContext(), CoupleUtil.getDanceCategoryColor(pointListRepository.getPointListCouple(coupleId, DanceType.LA).danceCategory))
        }

    val standardCategoryColor: Int
        get() = runBlocking {
            ContextCompat.getColor(MyApp.applicationContext(), CoupleUtil.getDanceCategoryColor(pointListRepository.getPointListCouple(coupleId, DanceType.ST).danceCategory))
        }

    val followImage: Int
        get() {
            return R.drawable.ic_following

//        return if (FakeFavoriteList.isFavorite(couple)) {
//            R.drawable.ic_following
//        } else {
//            R.drawable.ic_follow
//        }
        }

    fun toggleFavorite(): Int {
        return if (Math.random().toInt() % 2 == 0) {
            R.drawable.ic_following
        } else {
            R.drawable.ic_follow
        }
//        return if (FakeFavoriteList.isFavorite(couple)) {
//            FakeFavoriteList.removeCouple(couple)
//            R.drawable.ic_follow
//        } else {
//            FakeFavoriteList.addCouple(couple)
//            R.drawable.ic_following
//        }
    }

}