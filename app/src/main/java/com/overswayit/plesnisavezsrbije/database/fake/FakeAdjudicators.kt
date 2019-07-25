package com.overswayit.plesnisavezsrbije.database.fake

import com.overswayit.plesnisavezsrbije.models.Adjudicator
import com.overswayit.plesnisavezsrbije.models.FederationDanceType
import com.overswayit.plesnisavezsrbije.utils.AdjudicatorsUtil
import java.util.ArrayList

/**
 * Created by lazarristic on 2019-07-08.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
object FakeAdjudicators {
    fun getFakeAdjudicators(): ArrayList<Adjudicator> {
        val wdsf = "WDSF međunarodna licenca"
        val wdsfChair = "WDSF međunarodna licenca za Glavnog sudiju"
        val serbiaChar = "Licenca za Glavnog sudiju"
        val aLicence = "A licenca"

        val nesa = AdjudicatorsUtil.createAdjudicator("1", "Nenad Jeftić", "http://www.ples.co.rs/sudije/foto/001.jpg", FederationDanceType.LA_ST, wdsf, wdsfChair, serbiaChar)
        val buba = AdjudicatorsUtil.createAdjudicator("2", "Branislava Radovanović", "http://www.ples.co.rs/sudije/foto/002.jpg", FederationDanceType.LA_ST, wdsf, serbiaChar)
        val bombica = AdjudicatorsUtil.createAdjudicator("3", "Ivan Knežević", "http://www.ples.co.rs/sudije/foto/058.jpg", FederationDanceType.LA_ST, aLicence)
        val mile = AdjudicatorsUtil.createAdjudicator("4", "Ivan Mileusnić", "http://www.ples.co.rs/sudije/foto/063.jpg", FederationDanceType.LA_ST, aLicence)
        val gaga = AdjudicatorsUtil.createAdjudicator("5", "Dragana Labudović", "http://www.ples.co.rs/sudije/foto/060.jpg", FederationDanceType.MODERN, aLicence)
        val danijela = AdjudicatorsUtil.createAdjudicator("6", "Danijela Sagić", "http://www.ples.co.rs/sudije/foto/062.jpg", FederationDanceType.MODERN, aLicence)
        val miodrag = AdjudicatorsUtil.createAdjudicator("7", "Miodrag Mičić", "http://www.ples.co.rs/sudije/foto/043.jpg", FederationDanceType.MODERN, aLicence)

        val adjudicators = ArrayList<Adjudicator>()
        adjudicators.add(nesa)
        adjudicators.add(buba)
        adjudicators.add(bombica)
        adjudicators.add(mile)
        adjudicators.add(gaga)
        adjudicators.add(danijela)
        adjudicators.add(miodrag)

        return adjudicators
    }
}