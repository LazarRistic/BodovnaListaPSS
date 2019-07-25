package com.overswayit.plesnisavezsrbije.models

/**
 * Created by lazarristic on 2019-07-24.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
enum class League constructor(private val `val`: String) {
    SKOLSKA_I_PRVA("skolska_i_prva"),
    SUPER("super");

    fun asString(): String {
        return this.`val`
    }

    fun asUIString(): String {
        return this.`val`.replace("_", " ").capitalize()
    }

    companion object {
        fun fromUIString(type: String): League {
            for (leagueType in values()) {
                if (leagueType.asUIString() == type) {
                    return leagueType
                }
            }

            return SUPER
        }

        fun fromString(type: String): League {
            for (leagueType in values()) {
                if (leagueType.`val` == type) {
                    return leagueType
                }
            }

            return SUPER
        }
    }
}