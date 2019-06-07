package com.overswayit.plesnisavezsrbije.models

import java.util.*

/**
 * Created by lazarristic on 19/02/2019.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */

class Club {
    var id: Int = 0
    var logoUrl: String? = null
    var name: String? = null
    var town: String? = null
    var address: String? = null
    var contactName: String? = null
    var phoneNumbers = ArrayList<String>()
    var email: String? = null
}
