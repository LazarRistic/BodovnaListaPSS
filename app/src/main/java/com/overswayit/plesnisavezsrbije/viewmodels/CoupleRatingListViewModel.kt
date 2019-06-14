package com.overswayit.plesnisavezsrbije.viewmodels

import android.app.Application
import androidx.lifecycle.ViewModel
import com.overswayit.plesnisavezsrbije.models.RatingListItem
import com.overswayit.plesnisavezsrbije.utils.CoupleUtil

/**
 * Created by lazarristic on 2019-06-13.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
class CoupleRatingListViewModel(private val application: Application, private val latin: RatingListItem, private val standard: RatingListItem, private val combined: RatingListItem) : ViewModel() {

    val title: String
        get() {
            return CoupleUtil.getCoupleLastNames(latin.couple!!)
        }

    val pointsLatin: String?
        get() {
            return latin.points
        }

    val pointsStandard: String?
        get() {
            return standard.points
        }

    val pointsCombined: String?
        get() {
            return combined.points
        }

    val placeLatin: String?
        get() {
            return latin.place
        }

    val placeStandard: String?
        get() {
            return standard.place
        }

    val placeCombined: String?
        get() {
            return combined.place
        }
}