package com.overswayit.plesnisavezsrbije.utils

import android.text.TextUtils
import com.overswayit.plesnisavezsrbije.R
import com.overswayit.plesnisavezsrbije.models.AgeCategory
import com.overswayit.plesnisavezsrbije.models.Couple
import com.overswayit.plesnisavezsrbije.models.DanceCategory
import java.util.*

/**
 * Created by lazarristic on 2019-05-23.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
object CoupleUtil {

    fun getCoupleLastNames(couple: Couple): String {
        val maleLastName = if (!TextUtils.isEmpty(couple.nameMale)) {
            getLastName(couple.nameMale!!)
        } else {
            StringUtil.getString(R.string.not_available)
        }

        val femaleLastName = if (!TextUtils.isEmpty(couple.nameFemale)) {
            getLastName(couple.nameFemale!!)
        } else {
            StringUtil.getString(R.string.not_available)
        }

        return "$maleLastName - $femaleLastName"
    }

    fun getAgeCategoryColor(ageCategory: AgeCategory?): Int {
        return when (ageCategory) {
            AgeCategory.SENIOR -> R.color.color_primary_state_list
            AgeCategory.ADULT -> R.color.color_primary_state_list
            AgeCategory.YOUTH -> R.color.color_primary_state_list
            AgeCategory.JUNIOR_I -> R.color.color_primary_state_list
            AgeCategory.JUNIOR_II -> R.color.color_primary_state_list
            AgeCategory.JUVENILE -> R.color.color_primary_state_list
            else -> R.color.white
        }
    }

    fun getDanceCategoryColor(danceCategory: DanceCategory?): Int {
        return when (danceCategory) {
            DanceCategory.I -> R.color.color_class_i
            DanceCategory.A -> R.color.color_class_a
            DanceCategory.B -> R.color.color_class_b
            DanceCategory.C -> R.color.color_class_c
            DanceCategory.D -> R.color.color_class_d
            else -> R.color.white
        }
    }

    fun getPlaceHolderCouple(): Couple {
        return Couple(UUID.randomUUID().toString(), StringUtil.getString(R.string.not_available), StringUtil.getString(R.string.not_available), ClubUtil.getPlaceHolderClub())
    }

    private fun getLastName(fullName: String): String {
        return fullName.split(" ")[1]
    }
}