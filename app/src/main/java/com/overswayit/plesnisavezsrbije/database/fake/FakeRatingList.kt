package com.overswayit.plesnisavezsrbije.database.fake

import com.overswayit.plesnisavezsrbije.models.*

/**
 * Created by lazarristic on 2019-06-03.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
object FakeRatingList {

    fun getRatingListByDanceType(danceType: DanceType): List<RatingListItem> {
        return when (danceType) {
            DanceType.LA -> getLaRatingList()
            DanceType.ST -> getStRatingList()
            DanceType.KM -> getKmRatingList()
        }
    }

    private fun getLaRatingList(): List<RatingListItem> {
        return createRatingList(
                createRatingListItem(DanceType.LA, AgeCategory.SENIOR, "2", "689", "3"),
                createRatingListItem(DanceType.LA, AgeCategory.SENIOR, "3", "625", "1"),
                createRatingListItem(DanceType.LA, AgeCategory.SENIOR, "4", "360", "2"))
    }

    private fun getStRatingList(): List<RatingListItem> {
        return createRatingList(
                createRatingListItem(DanceType.ST, AgeCategory.SENIOR, "2", "503", "1"),
                createRatingListItem(DanceType.ST, AgeCategory.SENIOR, "12", "32", "3"),
                createRatingListItem(DanceType.ST, AgeCategory.SENIOR, "15", "0", "2"))
    }

    private fun getKmRatingList(): List<RatingListItem> {
        return createRatingList(
                createRatingListItem(DanceType.KM, AgeCategory.SENIOR, "1", "1076", "1"),
                createRatingListItem(DanceType.KM, AgeCategory.SENIOR, "4", "100", "3"),
                createRatingListItem(DanceType.KM, AgeCategory.SENIOR, "8", "0", "2"))
    }

    private fun createRatingList(vararg ratingListItems: RatingListItem): List<RatingListItem> {
        val ratingListItemList = ArrayList<RatingListItem>()
        ratingListItems.forEach {
            ratingListItemList.add(it)
        }

        return ratingListItemList
    }

    private fun createRatingListItem(danceType: DanceType, ageCategory: AgeCategory, place: String, points: String, id: String): RatingListItem {
        val ratingListItem = RatingListItem()
        ratingListItem.danceType = danceType
        ratingListItem.ageCategory = ageCategory
        ratingListItem.place = place
        ratingListItem.points = points
        ratingListItem.couple = FakeCouple.getCoupleById(id)

        return ratingListItem
    }
}