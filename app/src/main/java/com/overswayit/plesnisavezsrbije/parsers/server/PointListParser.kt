package com.overswayit.plesnisavezsrbije.parsers.server

import com.google.gson.internal.LinkedTreeMap
import com.overswayit.plesnisavezsrbije.models.DanceCategory
import com.overswayit.plesnisavezsrbije.models.DanceType
import com.overswayit.plesnisavezsrbije.models.PointListItem

/**
 * Created by lazarristic on 2019-07-16.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
object PointListParser {

    @Suppress("UNCHECKED_CAST")
    fun toPointListItemFromServerHashMap(map: LinkedTreeMap<String, Any>, type: DanceType): PointListItem {
        val maleHashMap = map["male"] as LinkedTreeMap<String, Any>
        val femaleHashMap = map["female"] as LinkedTreeMap<String, Any>
        val clubMap = map["club"] as LinkedTreeMap<String, Any>

        val categoryKey = when (type) {
            DanceType.LA -> "latin_category"
            DanceType.ST -> "standard_category"
            DanceType.KM -> "latin_category"
        }

        val pointsKey = when (type) {
            DanceType.LA -> "latin_points"
            DanceType.ST -> "standard_points"
            DanceType.KM -> "latin_points"
        }

        val placeKey = when (type) {
            DanceType.LA -> "latin_place"
            DanceType.ST -> "standard_place"
            DanceType.KM -> "latin_place"
        }

        val pointListItem = PointListItem(DanceCategory.fromString(maleHashMap[categoryKey] as String))

        val ageCategory = AgeCategoryParser.toAgeCategoryFromServerString(maleHashMap["age_category"] as String)
        val couple = CoupleParser.toCoupleFromServerHashMap(maleHashMap, femaleHashMap, clubMap)
        val points = maleHashMap[pointsKey] as String
        val place = maleHashMap[placeKey] as String

        pointListItem.ageCategory = ageCategory
        pointListItem.couple = couple
        pointListItem.danceType = type
        pointListItem.ageCategory
        pointListItem.points = points
        pointListItem.place = place

        return pointListItem
    }
}