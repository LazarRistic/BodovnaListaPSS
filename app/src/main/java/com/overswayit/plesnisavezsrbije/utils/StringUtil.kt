package com.overswayit.plesnisavezsrbije.utils

import androidx.annotation.StringRes
import com.overswayit.plesnisavezsrbije.App

/**
 * Created by lazarristic on 19/02/2019.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
object StringUtil {

    fun getString(@StringRes resId: Int, vararg formatArgs: Any): String {
        return App.getContext().getString(resId, *formatArgs)
    }
}
