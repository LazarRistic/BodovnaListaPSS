package com.overswayit.plesnisavezsrbije.parsers.server

import com.google.gson.internal.LinkedTreeMap
import com.overswayit.plesnisavezsrbije.models.Couple

/**
 * Created by lazarristic on 2019-07-16.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
object CoupleParser {

    fun toCoupleFromServerHashMap(maleMap: LinkedTreeMap<String, Any>, femaleMap: LinkedTreeMap<String, Any>, clubMap: LinkedTreeMap<String, Any>): Couple {
        val id = maleMap["id"] as String
        val nameMale = maleMap["name"] as String
        val nameFemale = femaleMap["name"] as String
        val club = ClubParser.toClubFromServerHashMap(clubMap)

        return Couple(id, nameMale, nameFemale, club)
    }

}