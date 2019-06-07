package com.overswayit.plesnisavezsrbije.models

import com.overswayit.plesnisavezsrbije.MyApp
import com.overswayit.plesnisavezsrbije.R

/**
 * Created by lazarristic on 26/02/2019.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
enum class DanceType(danceType: String) {
    LA(MyApp.applicationContext().getString(R.string.la)),
    ST(MyApp.applicationContext().getString(R.string.st)),
    KM(MyApp.applicationContext().getString(R.string.km));
}
