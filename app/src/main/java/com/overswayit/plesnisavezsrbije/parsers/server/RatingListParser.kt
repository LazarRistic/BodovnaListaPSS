package com.overswayit.plesnisavezsrbije.parsers.server

import com.google.gson.internal.LinkedTreeMap
import com.overswayit.plesnisavezsrbije.models.DanceCategory
import com.overswayit.plesnisavezsrbije.models.DanceType
import com.overswayit.plesnisavezsrbije.models.PointListItem
import com.overswayit.plesnisavezsrbije.models.RatingListItem

/**
 * Created by lazarristic on 2019-08-08.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
object RatingListParser {
    @Suppress("UNCHECKED_CAST")
    fun toRatingListItemFromServerHashMap(map: LinkedTreeMap<String, Any>, type: DanceType): RatingListItem {
        val maleHashMap = map["male"] as LinkedTreeMap<String, Any>
        val femaleHashMap = map["female"] as LinkedTreeMap<String, Any>
        val clubMap = map["club"] as LinkedTreeMap<String, Any>

        val categoryKey = "category"

        val pointsKey = when (type) {
            DanceType.LA -> "latin_points"
            DanceType.ST -> "standard_points"
            DanceType.KM -> "combined_points"
        }

        val placeKey = when (type) {
            DanceType.LA -> "latin_place"
            DanceType.ST -> "standard_place"
            DanceType.KM -> "combined_place"
        }

        val ratingListItem = RatingListItem()

        val ageCategory = AgeCategoryParser.toAgeCategoryFromServerString(maleHashMap["age_category"] as String)
        val couple = CoupleParser.toCoupleFromServerHashMap(maleHashMap, femaleHashMap, clubMap)
        val points = maleHashMap[pointsKey] as Double
        val place = maleHashMap[placeKey] as Double

        ratingListItem.ageCategory = ageCategory
        ratingListItem.couple = couple
        ratingListItem.danceType = type
        ratingListItem.points = points.toInt()
        ratingListItem.place = place.toInt()

        return ratingListItem
    }
}