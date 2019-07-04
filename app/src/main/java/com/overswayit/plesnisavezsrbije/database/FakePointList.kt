package com.overswayit.plesnisavezsrbije.database

import com.overswayit.plesnisavezsrbije.models.AgeCategory
import com.overswayit.plesnisavezsrbije.models.DanceCategory
import com.overswayit.plesnisavezsrbije.models.DanceType
import com.overswayit.plesnisavezsrbije.models.PointListItem

/**
 * Created by lazarristic on 2019-05-24.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
object FakePointList {

    fun getPointListByDanceType(danceType: DanceType): List<PointListItem> {
        return when (danceType) {
            DanceType.LA -> getLaPointList()
            DanceType.ST -> getStPointLisT()
            DanceType.KM -> emptyList()
        }
    }

    private fun getLaPointList(): List<PointListItem> {
        return createPointList(
                createPointListItem(DanceType.LA, AgeCategory.SENIOR, DanceCategory.A, "1", "744", "1"),
                createPointListItem(DanceType.LA, AgeCategory.SENIOR, DanceCategory.A, "2", "649", "2"),
                createPointListItem(DanceType.LA, AgeCategory.SENIOR, DanceCategory.A, "4", "530", "3"))

    }

    private fun getStPointLisT(): List<PointListItem> {
        return createPointList(
                createPointListItem(DanceType.ST, AgeCategory.SENIOR, DanceCategory.A, "2", "628", "1"),
                createPointListItem(DanceType.ST, AgeCategory.SENIOR, DanceCategory.A, "5", "0", "2"),
                createPointListItem(DanceType.ST, AgeCategory.SENIOR, DanceCategory.B, "7", "148", "3"))

    }

    private fun createPointList(vararg pointListItems: PointListItem): List<PointListItem> {
        val pointListItemList = ArrayList<PointListItem>()
        pointListItems.forEach {
            pointListItemList.add(it)
        }

        return pointListItemList
    }

    private fun createPointListItem(danceType: DanceType, ageCategory: AgeCategory, danceCategory: DanceCategory, place: String, points: String, id: String): PointListItem {
        val pointListItem = PointListItem(danceCategory)
        pointListItem.danceType = danceType
        pointListItem.ageCategory = ageCategory
        pointListItem.place = place
        pointListItem.points = points
        pointListItem.couple = FakeCouple.getCoupleById(id)

        return pointListItem
    }
}