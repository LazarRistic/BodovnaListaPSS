package com.overswayit.plesnisavezsrbije.utils

import com.overswayit.plesnisavezsrbije.R
import com.overswayit.plesnisavezsrbije.models.*
import java.util.*

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

    fun getClubNameAndTown(name: String? = StringUtil.getString(R.string.not_available), town: String? = StringUtil.getString(R.string.not_available)): String {
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
}
