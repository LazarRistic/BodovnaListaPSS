package com.overswayit.plesnisavezsrbije.utils

import com.overswayit.plesnisavezsrbije.R
import com.overswayit.plesnisavezsrbije.models.Adjudicator
import com.overswayit.plesnisavezsrbije.models.AdjudicatorLicensesType

/**
 * Created by lazarristic on 2019-07-08.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
object AdjudicatorsUtil {
    fun createAdjudicator(id: String, name: String, avatarUri: String, licencesType: AdjudicatorLicensesType, vararg licences: String): Adjudicator {
        val list = ArrayList<String>()
        licences.forEach {
            list.add(it)
        }

        return Adjudicator(id, avatarUri, name, list, licencesType, R.color.white)
    }
}