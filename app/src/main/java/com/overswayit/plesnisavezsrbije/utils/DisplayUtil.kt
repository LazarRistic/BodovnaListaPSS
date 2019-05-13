package com.overswayit.plesnisavezsrbije.utils

import com.overswayit.plesnisavezsrbije.App

/**
 * Created by lazarristic on 2019-05-08.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
object DisplayUtil {

    fun dpToPx(dp: Int): Int {
        val density = App.context.resources.displayMetrics.density
        return Math.round(dp.toFloat() * density)
    }
}