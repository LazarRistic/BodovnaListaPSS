package com.overswayit.plesnisavezsrbije.utils;

import com.overswayit.plesnisavezsrbije.App;

import androidx.annotation.StringRes;

/**
 * Created by lazarristic on 19/02/2019.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
public class StringUtil {

    public static String getString(@StringRes int resId, Object... formatArgs) {
        return App.getContext().getString(resId, formatArgs);
    }
}
