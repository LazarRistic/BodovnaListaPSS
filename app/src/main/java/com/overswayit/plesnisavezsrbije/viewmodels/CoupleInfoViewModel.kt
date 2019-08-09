package com.overswayit.plesnisavezsrbije.viewmodels

import android.app.Application
import androidx.core.content.ContextCompat
import androidx.lifecycle.*
import com.overswayit.plesnisavezsrbije.MyApp
import com.overswayit.plesnisavezsrbije.R
import com.overswayit.plesnisavezsrbije.models.*
import com.overswayit.plesnisavezsrbije.networking.ListApiService
import com.overswayit.plesnisavezsrbije.repository.FavoriteCouplesRepository
import com.overswayit.plesnisavezsrbije.repository.ListRepository
import com.overswayit.plesnisavezsrbije.utils.CoupleUtil
import kotlinx.coroutines.*

/**
 * Created by lazarristic on 2019-06-14.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
class CoupleInfoViewModel(val application: Application, private val coupleId: String) : ViewModel() {
    private val pointListRepository = ListRepository(application, ListApiService.LIST_API)
    private val favoriteCouplesRepository: FavoriteCouplesRepository = FavoriteCouplesRepository(application)
    private val isFollowingObserver = MediatorLiveData<Int>()
    private lateinit var isFollowingLiveData: LiveData<Int>
    private lateinit var couple: Couple

    init {
        viewModelScope.launch {
            couple = pointListRepository.getPointListCouple(coupleId, DanceType.LA).couple

            isFollowingLiveData = Transformations.map(favoriteCouplesRepository.findFavoriteCoupleById(couple)) {
                favorite -> transformFavoriteToIcon(favorite)
            }

            isFollowingObserver.addSource(isFollowingLiveData, isFollowingObserver::setValue)
        }
    }

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

    val followImage: LiveData<Int> = isFollowingObserver

    fun toggleFavorite() {
        viewModelScope.launch(Dispatchers.IO) {
            favoriteCouplesRepository.toggleFavorite(couple)
        }
    }

    private fun transformFavoriteToIcon(favorite: Boolean): Int {
        return if (favorite) {
            R.drawable.ic_following
        } else {
            R.drawable.ic_follow
        }
    }
}