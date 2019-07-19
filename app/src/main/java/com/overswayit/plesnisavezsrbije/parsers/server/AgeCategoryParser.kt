package com.overswayit.plesnisavezsrbije.parsers.server

import com.overswayit.plesnisavezsrbije.models.AgeCategory

/**
 * Created by lazarristic on 2019-07-10.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
object AgeCategoryParser {
    fun toAgeCategoryFromServerString(sCategory: String): AgeCategory {
        return when (sCategory) {
            "G" -> AgeCategory.JUVENILE
            "H" -> AgeCategory.JUVENILE
            "I" -> AgeCategory.JUNIOR_I
            "J" -> AgeCategory.JUNIOR_II
            "K" -> AgeCategory.YOUTH
            "L" -> AgeCategory.ADULT
            "M" -> AgeCategory.SENIOR
            else -> AgeCategory.JUVENILE
        }
    }

    fun toServerStringFromAgeCategory(ageCategory: AgeCategory): String {
        return when (ageCategory) {
            AgeCategory.JUVENILE -> "G"
            AgeCategory.JUNIOR_I -> "I"
            AgeCategory.JUNIOR_II -> "J"
            AgeCategory.YOUTH -> "K"
            AgeCategory.ADULT -> "L"
            AgeCategory.SENIOR -> "M"
        }
    }
}