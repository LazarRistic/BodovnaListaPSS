package com.overswayit.plesnisavezsrbije.parsers.server

import com.google.gson.internal.LinkedTreeMap
import com.overswayit.plesnisavezsrbije.models.Club
import com.overswayit.plesnisavezsrbije.utils.ClubUtil.getLogoUrl
import com.overswayit.plesnisavezsrbije.utils.ClubUtil.getPhonesFromServerHashMap

/**
 * Created by lazarristic on 2019-07-11.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
object ClubParser {

    fun toClubFromServerHashMap(map: LinkedTreeMap<String, Any>): Club {
        val id = map["id"] as String
        val name = map["name"] as String
        val town = map["town"] as String
        val country = map["country"] as String
        val address = map["address"] as String
        val email = map["email"] as String
        val web = map["web"] as String
        val contactName = map["contact"] as String
        val phones = getPhonesFromServerHashMap(map)
        val logoUrl = getLogoUrl(id)

        return Club(id, logoUrl, name, town, country, address, web, contactName, phones, email)
    }
}