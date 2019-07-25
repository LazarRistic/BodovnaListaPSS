package com.overswayit.plesnisavezsrbije.viewmodels

import com.overswayit.plesnisavezsrbije.MyApp
import com.overswayit.plesnisavezsrbije.models.Adjudicator
import com.overswayit.plesnisavezsrbije.utils.AdjudicatorsUtil

/**
 * Created by lazarristic on 21/02/2019.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
class AdjudicatorViewModel(private val adjudicator: Adjudicator) {

    val name: String?
        get() = adjudicator.name

    val initials: String?
        get() = AdjudicatorsUtil.getInitials(adjudicator.name)

    val licenses: String
        get() {
            val licenses = StringBuilder()

            for (license in adjudicator.licenses) {
                licenses.append(license).append(System.getProperty("line.separator"))
            }

            return licenses.toString()
        }

    val avatarUri: String?
        get() = adjudicator.avatarUrl

    val borderColor: Int
        get() = MyApp.applicationContext().getColor(adjudicator.color)
}
