package com.overswayit.plesnisavezsrbije.viewmodels

import android.app.Application
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import com.overswayit.plesnisavezsrbije.MyApp
import com.overswayit.plesnisavezsrbije.R
import com.overswayit.plesnisavezsrbije.models.DanceCategory
import com.overswayit.plesnisavezsrbije.models.PointListItem
import com.overswayit.plesnisavezsrbije.utils.CoupleUtil

/**
 * Created by lazarristic on 2019-06-13.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
class CouplePointListViewModel(private val application: Application, private val latin: PointListItem, private val standard: PointListItem) : ViewModel() {

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

    val placeLatin: String?
        get() {
            return latin.place
        }

    val placeStandard: String?
        get() {
            return standard.place
        }

    val danceCategoryLatin: String?
        get() {
            return danceCategoryToString(latin.danceCategory)

        }

    val danceCategoryStandard: String?
        get() {
            return danceCategoryToString(standard.danceCategory)
        }

    val danceCategoryColorLatin: Int
        get() {
            return ContextCompat.getColor(MyApp.applicationContext(), CoupleUtil.getDanceCategoryColor(latin.danceCategory))
        }

    val danceCategoryColorStandard: Int
        get() {
            return ContextCompat.getColor(MyApp.applicationContext(), CoupleUtil.getDanceCategoryColor(standard.danceCategory))
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