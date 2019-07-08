package com.overswayit.plesnisavezsrbije.models

/**
 * Created by lazarristic on 2019-05-09.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
enum class AdjudicatorLicensesType constructor(private val `val`: String) {
    LA_ST("la_st"),
    MODERN("modern");

    fun asString(): String {
        return this.`val`
    }

    companion object {

        fun fromString(type: String): AdjudicatorLicensesType {
            for (licensesType in values()) {
                if (licensesType.`val` == type) {
                    return licensesType
                }
            }

            return LA_ST
        }
    }
}
