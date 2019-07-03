package com.overswayit.plesnisavezsrbije.database

import com.overswayit.plesnisavezsrbije.models.Club

/**
 * Created by lazarristic on 2019-05-24.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
object FakeClubs {

    fun getClubById(id: Int): Club? {
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
        val best = createClub(100,
                "Best",
                "Kružni Put Kijevo 21v",
                "Beograd",
                "Branislava Radovanović",
                "bubarada70@gmail.com",
                "K01446.jpg",
                "0605523289", "0112337082")

        val calypso = createClub(101,
                "Calypso",
                "Miloja Zakića 1/7",
                "Beograd",
                "Dragana Labudović",
                "pkcalypso.dragana@gmail.com",
                "K00066.jpg",
                "063/82 82 225")

        val dareToDance = createClub(102,
                "Dare To Dance",
                "Kirovljeva 15",
                "Beograd",
                "Ivan Mileusnić",
                "daretodance@gmail.com",
                "K01300.jpg",
                "0691700760")

        val beodance = createClub(103,
                "Beodance",
                "Gospodar Jovanova 22",
                "Beograd",
                "Nenad Jeftić",
                "beodance@eunet.rs",
                "K00002.jpg",
                "011/328-39-46")

        return createClubList(beodance, dareToDance, calypso, best)
    }

    private fun createClub(id: Int, name: String, address: String, town: String, contactName: String, email: String, logoUrl: String? = "", vararg phoneNumbers: String): Club {
        val phoneNumberList = ArrayList<String>()

        phoneNumbers.forEach {
            phoneNumberList.add(it)
        }

        return Club(id, "http://www.ples.co.rs/klubovi/logo/$logoUrl", name, town, address, contactName, phoneNumberList, email)
    }

    private fun createClubList(vararg clubs: Club): List<Club> {
        val clubList = ArrayList<Club>()

        clubs.forEach {
            clubList.add(it)
        }

        return clubList
    }
}