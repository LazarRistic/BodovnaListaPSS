package com.overswayit.plesnisavezsrbije.models

/**
 * Created by lazarristic on 25/02/2019.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
enum class AgeCategory private constructor(private val `val`: String) {
    JUVENILE("PIO"),
    JUNIOR_I("MLO"),
    JUNIOR_II("OML"),
    YOUTH("STO"),
    ADULT("SEN"),
    SENIOR("STS");

    fun asString(): String {
        return this.`val`
    }

    companion object {

        fun fromString(category: String): AgeCategory? {
            for (ageCategory in values()) {
                if (ageCategory.`val` == category) {
                    return ageCategory
                }
            }

            return null
        }
    }
}
