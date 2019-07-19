package com.overswayit.plesnisavezsrbije.database.fake

import com.overswayit.plesnisavezsrbije.models.Club
import com.overswayit.plesnisavezsrbije.utils.ClubUtil
import com.overswayit.plesnisavezsrbije.utils.ClubUtil.getLogoUrl

/**
 * Created by lazarristic on 2019-05-24.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
object FakeClubs {

    fun getClubById(id: String): Club? {
        getAllClubs().forEach {
            if (it.id == id) {
                return it
            }
        }

        return null
    }

    fun getClubByName(name: String): Club? {
        getAllClubs().forEach {
            if (it.name.toLowerCase() == name.toLowerCase()) {
                return it
            }
        }

        return null
    }

    fun getAllClubs(): List<Club> {
        val best = createClub("1446",
                getLogoUrl("1446"),
                "Best",
                "Calypso",
                "Beograd",
                "Kružni Put Kijevo 21v",
                "www.danceclubbest.com",
                "Branislava Radovanović",
                "bubarada70@gmail.com",
                "0605523289", "0112337082")

        val calypso = createClub("66",
                getLogoUrl("66"),
                "Calypso",
                "Beograd",
                "Serbia",
                "Miloja Zakića 1/7",
                "www.pkcalypso.rs",
                "Dragana Labudović",
                "pkcalypso.dragana@gmail.com",
                "063/82 82 225")

        val dareToDance = createClub("1300",
                getLogoUrl("1300"),
                "Dare To Dance",
                "Beograd",
                "Serbia",
                "Kirovljeva 15",
                "www.dare2daance.rs",
                "Ivan Mileusnić",
                "daretodance@gmail.com",
                "0691700760")

        val beodance = createClub("2",
                getLogoUrl("2"),
                "Beodance",
                "Beograd",
                "Serbia",
                "Gospodar Jovanova 22",
                "www.beodance.rs",
                "Nenad Jeftić",
                "beodance@eunet.rs",
                "011/328-39-46")

        return createClubList(beodance, dareToDance, calypso, best)
    }

    private fun createClub(id: String, idNumber: String, logoUrl: String = "", name: String, town: String, country: String, address: String, web: String, contactName: String, email: String, vararg phoneNumbers: String): Club {
        val phoneNumberList = ArrayList<String>()

        phoneNumbers.forEach {
            phoneNumberList.add(it)
        }

        return Club(id, logoUrl, name, town, country, address, web, contactName, phoneNumberList, email)
    }

    private fun createClubList(vararg clubs: Club): List<Club> {
        val clubList = ArrayList<Club>()

        clubs.forEach {
            clubList.add(it)
        }

        return clubList
    }
}