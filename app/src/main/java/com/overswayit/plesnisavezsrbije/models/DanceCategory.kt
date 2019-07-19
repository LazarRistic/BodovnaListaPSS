package com.overswayit.plesnisavezsrbije.models

/**
 * Created by lazarristic on 25/02/2019.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
enum class DanceCategory constructor(private val `val`: String) {
    D("D"),
    C("C"),
    B("B"),
    A("A"),
    I("I");

    fun asString(): String {
        return this.`val`
    }

    companion object {

        fun fromString(category: String): DanceCategory {
            for (danceCategory in values()) {
                if (danceCategory.`val` == category) {
                    return danceCategory
                }
            }

            return D
        }
    }
}
