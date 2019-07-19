package com.overswayit.plesnisavezsrbije.utils

import android.text.TextUtils
import com.google.gson.JsonObject
import com.google.gson.internal.LinkedHashTreeMap
import com.google.gson.internal.LinkedTreeMap
import com.overswayit.plesnisavezsrbije.R
import com.overswayit.plesnisavezsrbije.models.*
import com.overswayit.plesnisavezsrbije.services.JsonService

/**
 * Created by lazarristic on 18/04/2019.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
object ClubUtil {

    fun getClubNameAndTown(club: Club?): String {
        return if (club != null) {
            getClubNameAndTown(club.name, club.town)
        } else StringUtil.getString(R.string.not_available)
    }

    private fun getClubNameAndTown(name: String? = StringUtil.getString(R.string.not_available), town: String? = StringUtil.getString(R.string.not_available)): String {
        return StringBuilder().append(name).append(" - ").append(town).toString()
    }

    fun getClubContacts(club: Club): ArrayList<ClubContact> {
        val clubContacts = ArrayList<ClubContact>()

        clubContacts.add(AddressClubContact(club.address))
        clubContacts.add(EmailClubContact(club.email))

        val clubNumbers = club.phoneNumbers

        for (phone in clubNumbers) {
            clubContacts.add(PhoneClubContact(phone))
        }

        return clubContacts
    }

    fun getPlaceHolderClub(): Club {
        val list = ArrayList<String>()
        list.add("+381111111111")
        return Club("999999", getLogoUrl("0"), StringUtil.getString(R.string.not_available), "Beograd", "Serbia", "Knez Mihajlova 1", "", "Plesni Savez Srbije", list, "pss@example.com")
    }

    fun getLogoUrl(idNumber: String): String {
        return "http://www.ples.co.rs/klubovi/logo/K" + idNumber.padStart(5, '0') + ".jpg"
    }

    fun getPhonesFromServerHashMap(map: LinkedTreeMap<String, Any>): ArrayList<String> {
        return addClubPhone(map, "phone_1", "phone_2", "phone_3")
    }

    private fun addClubPhone(map: LinkedTreeMap<String, Any>, vararg properties: String): ArrayList<String> {
        val phoneList = ArrayList<String>()

        properties.forEach {
            val phone = map[it] as String

            if (!TextUtils.isEmpty(phone)) {
                phoneList.add(phone)
            }
        }

        return phoneList
    }

    private fun addClubPhone(default: String = "", json: JsonObject, vararg properties: String): ArrayList<String> {
        val phoneList = ArrayList<String>()

        properties.forEach {
            val phone = JsonService.asString(JsonService.getProperty(json, it), default)

            if (!TextUtils.isEmpty(phone)) {
                phoneList.add(phone)
            }
        }

        return phoneList
    }
}
