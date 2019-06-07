package com.overswayit.plesnisavezsrbije.utils

import android.text.TextUtils
import com.overswayit.plesnisavezsrbije.R
import com.overswayit.plesnisavezsrbije.models.Couple

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

    private fun getLastName(fullName: String): String {
        return fullName.split(" ")[1]
    }
}