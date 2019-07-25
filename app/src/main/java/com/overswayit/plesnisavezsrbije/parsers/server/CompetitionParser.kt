package com.overswayit.plesnisavezsrbije.parsers.server

import com.google.gson.internal.LinkedTreeMap
import com.overswayit.plesnisavezsrbije.models.*

/**
 * Created by lazarristic on 2019-07-24.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
object CompetitionParser {

    fun toCompetitionEventFromServerServerHashMap(map: LinkedTreeMap<String, Any>): CompetitionEvent {
        val id = map["id"] as Double
        val town = map["town"] as String
        val organiser = (map["organiser"] as String).replace("<br>", "\n").replace("</br>", "\n")
        val gym = map["gym"] as String?
        val date = getDatesFromServerHashMap(map)
        val competitions = getCompetitionsFromServerHashMap(map)

        return CompetitionEvent(id.toInt(), date, organiser, town, gym, competitions)
    }

    @Suppress("UNCHECKED_CAST")
    private fun getDatesFromServerHashMap(map: LinkedTreeMap<String, Any>): ArrayList<String> {
        val arrayList = map["date"] as ArrayList<Any>
        val list = ArrayList<String>()

        arrayList.forEach {
            val date = (it as String).split(".")

            val builder = StringBuilder()
            builder.append(date[0]).append(". ").append(date[1].capitalize())
            list.add(builder.toString())
        }

        return list
    }

    @Suppress("UNCHECKED_CAST")
    private fun getCompetitionsFromServerHashMap(map: LinkedTreeMap<String, Any>): ArrayList<Competition> {
        val list = ArrayList<Competition>()
        list.addAll(toCompetitionsFromServerHashMapByFederationDanceType(map, FederationDanceType.LA_ST))
        list.addAll(toCompetitionsFromServerHashMapByFederationDanceType(map, FederationDanceType.MODERN))

        return list
    }

    @Suppress("UNCHECKED_CAST")
    private fun toCompetitionsFromServerHashMapByFederationDanceType(map: LinkedTreeMap<String, Any>, federationDanceType: FederationDanceType): List<Competition> {
        val list = ArrayList<Competition>()

        val schoolAndFirstLeague = toCompetitionsFromServerHashMapByFederationDanceTypeAndLeague(map[federationDanceType.asString()] as LinkedTreeMap<String, Any>, federationDanceType, League.SKOLSKA_I_PRVA)
        val superLeague = toCompetitionsFromServerHashMapByFederationDanceTypeAndLeague(map[federationDanceType.asString()] as LinkedTreeMap<String, Any>, federationDanceType, League.SUPER)

        if (schoolAndFirstLeague != null) {
            list.add(schoolAndFirstLeague)
        }

        if (superLeague != null){
            list.add(superLeague);
        }

        return list
    }

    @Suppress("UNCHECKED_CAST")
    private fun toCompetitionsFromServerHashMapByFederationDanceTypeAndLeague(map: LinkedTreeMap<String, Any>, federationDanceType: FederationDanceType, league: League): Competition? {
        return if (map.contains(league.asString()) && map[league.asString()] is LinkedTreeMap<*, *>){
            toCompetitionFromServerHashMap(map[league.asString()] as LinkedTreeMap<String, Any>, league, federationDanceType)
        } else {
            null
        }
    }

    @Suppress("UNCHECKED_CAST")
    private fun toCompetitionFromServerHashMap(map: LinkedTreeMap<String, Any>, league: League, type: FederationDanceType): Competition {
        val categories = getCategoriesFromServerHashMap(map["category"] as ArrayList<Any>)
        val docs = getDocsFromServerHashMap(map["docs"] as ArrayList<Any>)

        return Competition(league, type, categories, docs)
    }

    private fun getCategoriesFromServerHashMap(categories: ArrayList<Any>): ArrayList<String> {
        return getStringListFromServerHashMap(categories)
    }

    private fun getDocsFromServerHashMap(docs: ArrayList<Any>): ArrayList<String> {
        return getStringListFromServerHashMap(docs)
    }

    private fun getStringListFromServerHashMap(arrayList: ArrayList<Any>): ArrayList<String> {
        val list = ArrayList<String>()
        arrayList.forEach {
            list.add(it as String)
        }

        return list
    }
}