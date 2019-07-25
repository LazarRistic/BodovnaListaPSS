package com.overswayit.plesnisavezsrbije.utils

import android.text.TextUtils
import com.overswayit.plesnisavezsrbije.models.Competition
import com.overswayit.plesnisavezsrbije.models.CompetitionEvent
import com.overswayit.plesnisavezsrbije.models.FederationDanceType

/**
 * Created by lazarristic on 2019-07-24.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
object CompetitionsUtil {

    fun getDateForUI(dates: ArrayList<String>): String {
        val date = StringBuilder()
        dates.forEach {
            date.append(it).append(" / ")
        }

        return date.substring(0, date.length - 3)
    }

    fun getLocationForUI(town: String, gym: String?): String {
        return if (TextUtils.isEmpty(gym)) {
            town
        } else {
            StringBuilder(town).append(", ").append(gym).toString()
        }
    }

    fun getCompetitionForDanceType(competitionEvent: CompetitionEvent, danceType: FederationDanceType): List<Competition> {
        val list = ArrayList<Competition>()

        competitionEvent.competition.forEach {
            if (danceType == it.type) {
                list.add(it)
            }
        }

        return list
    }

    fun getCompetitionCategories(categories: java.util.ArrayList<String>): String {
        val category = StringBuilder()
        categories.forEach {
            category.append(it).append("\n")
        }

        return category.substring(0, category.length - 1)
    }

}