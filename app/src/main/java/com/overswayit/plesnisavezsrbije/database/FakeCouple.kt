package com.overswayit.plesnisavezsrbije.database

import com.overswayit.plesnisavezsrbije.models.*

/**
 * Created by lazarristic on 2019-05-24.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
object FakeCouple {

    fun getCoupleByName(name: String): Couple? {
        getAllCouples().forEach {
            if (name == it.nameMale || name == it.nameFemale) {
                return it
            }
        }

        return null
    }

    fun getCoupleById(id: String): Couple? {
        getAllCouples().forEach {
            if (id == it.id) {
                return it
            }
        }

        return null
    }

    fun getAllCouples(): List<Couple> {

        val maticKlenovski = createCouple("1",
                "Aleksandar Matovic",
                "Jovana Klenkovski",
//                createPointList(AgeCategory.SENIOR, DanceCategory.A, "1", "744"),
//                createPointList(AgeCategory.SENIOR, DanceCategory.I, "2", "616"),
//                createRatingList(AgeCategory.SENIOR, "2", "701"),
//                createRatingList(AgeCategory.SENIOR, "2", "576"),
//                createRatingList(AgeCategory.SENIOR, "2", "1260"),
                FakeClubs.getClubByName("beodance"))

        val kranikBoronzan = createCouple("2",
                "Djordje Kranik",
                "Marina Boronzan",
//                createPointList(AgeCategory.SENIOR, DanceCategory.A, "2", "649"),
//                createPointList(AgeCategory.SENIOR, DanceCategory.A, "5", "0"),
//                createRatingList(AgeCategory.SENIOR, "5", "370"),
//                createRatingList(AgeCategory.SENIOR, "15", "0"),
//                createRatingList(AgeCategory.SENIOR, "8", "0"),
                FakeClubs.getClubByName("best"))

        val vukobratGavrilov = createCouple("3",
                "Petar Vukobrat",
                "Gavrilov Kristina",
//                createPointList(AgeCategory.SENIOR, DanceCategory.A, "4", "530"),
//                createPointList(AgeCategory.SENIOR, DanceCategory.B, "4", "136"),
//                createRatingList(AgeCategory.SENIOR, "3", "697"),
//                createRatingList(AgeCategory.SENIOR, "12", "32"),
//                createRatingList(AgeCategory.SENIOR, "4", "100"),
                FakeClubs.getClubByName("dare to dance"))

        return createCoupleList(maticKlenovski, kranikBoronzan, vukobratGavrilov)
    }

    private fun createCoupleList(vararg couples: Couple): List<Couple> {
        val couplesList = ArrayList<Couple>()

        couples.forEach {
            couplesList.add(it)
        }

        return couplesList
    }

    private fun createCouple(id: String, nameMale: String, nameFemale: String,
                             club: Club?): Couple {
        val couple = Couple()
        couple.id = id
        couple.nameMale = nameMale
        couple.nameFemale = nameFemale
        couple.club = club

        return couple
    }

    private fun createRatingList(ageCategory: AgeCategory, place: String, points: String): RateingList {
        val ratingList = RateingList()
        ratingList.ageCategory = ageCategory
        ratingList.place = place
        ratingList.points = points

        return ratingList
    }

    private fun createPointList(ageCategory: AgeCategory, danceCategory: DanceCategory, place: String, points: String): PointListItem {
        val pointList = PointListItem()
        pointList.ageCategory = ageCategory
        pointList.danceCategory = danceCategory
        pointList.place = place
        pointList.points = points

        return pointList
    }
}