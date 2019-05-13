package com.overswayit.plesnisavezsrbije.models

import android.util.Pair

import java.util.ArrayList

/**
 * Created by lazarristic on 18/04/2019.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
abstract class ClubContact internal constructor(var contact: String?) {

    var type: ClubContactType? = null
    var contacts: ArrayList<Pair<String?, Int>>? = null

}
