package com.overswayit.plesnisavezsrbije.utils

import com.overswayit.plesnisavezsrbije.MyApp

/**
 * Created by lazarristic on 2019-07-25.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
object MeasurementUtils {

    fun toDensityIndependentPixels(value: Int): Float {
        val d = MyApp.applicationContext().resources.displayMetrics.density
        return (value * d)
    }
}