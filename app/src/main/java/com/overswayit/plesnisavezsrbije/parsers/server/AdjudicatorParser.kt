package com.overswayit.plesnisavezsrbije.parsers.server

import android.text.TextUtils
import com.google.gson.internal.LinkedTreeMap
import com.overswayit.plesnisavezsrbije.R
import com.overswayit.plesnisavezsrbije.models.Adjudicator
import com.overswayit.plesnisavezsrbije.models.FederationDanceType
import com.overswayit.plesnisavezsrbije.utils.StringUtil

/**
 * Created by lazarristic on 2019-07-12.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
object AdjudicatorParser {
    fun toAdjudicatorFromServerHashMap(map: LinkedTreeMap<String, Any>): Adjudicator {
        val id = map["id"] as String
        val name = map["name"] as String
        val type = FederationDanceType.fromString(map["type"] as String)
        val picUrl = map["pic"] as String
        val color = getAdjudicatorColor(map["color"] as String)

        @Suppress("UNCHECKED_CAST")
        val licenses = getLicenses(map["licenses"] as ArrayList<Any>)

        return Adjudicator(id, picUrl, name, licenses, type, color)
    }

    private fun getLicenses(list: ArrayList<Any>): ArrayList<String> {
        val licenses = StringBuilder()
        val lics = ArrayList<String>()

        if (5 == list.size) {
            if (!TextUtils.isEmpty(list[0] as String)) {
                licenses.append(getLicensesStringFromLetter(list[0] as String)).append("/n")
                lics.add(getLicensesStringFromLetter(list[0] as String))
            }

            if (!TextUtils.isEmpty(list[1] as String)) {
                licenses.append(getLicensesStringFromLetter(list[1] as String)).append("/n")
                lics.add(getLicensesStringFromLetter(list[1] as String))
            }

            if (licenses.isNotEmpty()) {
                licenses.append("/n")
            }

            if (!TextUtils.isEmpty(list[3] as String)) {
                licenses.append(StringUtil.getString(R.string.dance_category_license, list[3] as String)).append("/n")
                lics.add(StringUtil.getString(R.string.dance_category_license, list[3] as String))
            }

            if (!TextUtils.isEmpty(list[4] as String)) {
                licenses.append(getLicensesStringFromLetter(list[4] as String))
                lics.add(getLicensesStringFromLetter(list[4] as String))
            }
        }

        return lics
    }

    private fun getLicensesStringFromLetter(letter: String): String {
        return when (letter) {
            "I" -> StringUtil.getString(R.string.wdsf_license)
            "C" -> StringUtil.getString(R.string.wdsf_licensed_chairman)
            "G" -> StringUtil.getString(R.string.licensed_chairman)
            else -> ""
        }
    }

    private fun getAdjudicatorColor(color: String): Int {
        return when (color) {
            "5" -> R.color.adjudicator_color_5
            "6" -> R.color.adjudicator_color_6
            "7" -> R.color.adjudicator_color_7
            "8" -> R.color.adjudicator_color_8
            "9" -> R.color.adjudicator_color_9
            else -> R.color.white
        }
    }
}