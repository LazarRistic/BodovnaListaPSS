package com.overswayit.plesnisavezsrbije.viewmodels

import android.app.Application
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import com.overswayit.plesnisavezsrbije.MyApp
import com.overswayit.plesnisavezsrbije.models.AgeCategory
import com.overswayit.plesnisavezsrbije.models.Couple
import com.overswayit.plesnisavezsrbije.models.DanceCategory
import com.overswayit.plesnisavezsrbije.utils.CoupleUtil

/**
 * Created by lazarristic on 2019-06-14.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
class CoupleInfoViewModel(val application: Application, private val couple: Couple, private val ageCategory: AgeCategory, private val latinCategory: DanceCategory, private val standardCategory: DanceCategory) : ViewModel() {

    val clubTown: String?
        get() {
            return couple.club!!.town
        }

    val clubName: String?
        get() {
            return couple.club!!.name
        }

    val coupleImageUrl: String?
        get() {
            return "https://i.ibb.co/RDzdjpg/FIL-2903.jpg"
        }

    val clubLogoUrl: String?
        get() {
            return couple.club!!.logoUrl
        }

    val nameMale: String?
        get() {
            return couple.nameMale
        }

    val nameFemale: String?
        get() {
            return couple.nameFemale
        }

    val ageCategoryText: String?
        get() {
            return ageCategory.toString()
        }

    val ageCategoryColor: Int
        get() {
            return ContextCompat.getColor(MyApp.applicationContext(), CoupleUtil.getAgeCategoryColor(ageCategory))
        }

    val latinCategoryText: String?
        get() {
            return latinCategory.toString()
        }

    val standardCategoryText: String?
        get() {
            return standardCategory.toString()
        }

    val latinCategoryColor: Int
        get() {
            return ContextCompat.getColor(MyApp.applicationContext(), CoupleUtil.getDanceCategoryColor(latinCategory))
        }

    val standardCategoryColor: Int
        get() {
            return ContextCompat.getColor(MyApp.applicationContext(), CoupleUtil.getDanceCategoryColor(standardCategory))
        }

}