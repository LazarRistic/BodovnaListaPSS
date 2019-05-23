package com.overswayit.plesnisavezsrbije.utils

import com.overswayit.plesnisavezsrbije.MyApp

/**
 * Created by lazarristic on 2019-05-08.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
object DisplayUtil {

    fun dpToPx(dp: Int): Int {
        val density = MyApp.applicationContext().resources.displayMetrics.density
        return Math.round(dp.toFloat() * density)
    }
}