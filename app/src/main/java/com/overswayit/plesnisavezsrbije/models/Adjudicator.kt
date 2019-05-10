package com.overswayit.plesnisavezsrbije.models

import java.util.ArrayList

/**
 * Created by lazarristic on 21/02/2019.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
class Adjudicator {
    var id: String? = null
    var avatarUrl: String? = null
    var name: String? = null
    var licenses = ArrayList<String>()
    var licensesType: AdjudicatorLicensesType? = null

    fun addLicense(license: String) {
        licenses.add(license)
    }
}
