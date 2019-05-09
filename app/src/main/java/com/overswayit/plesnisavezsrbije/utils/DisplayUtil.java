package com.overswayit.plesnisavezsrbije.utils;

import com.overswayit.plesnisavezsrbije.App;

/**
 * Created by lazarristic on 2019-05-08.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
public class DisplayUtil {

    public static int dpToPx(int dp) {
        float density = App.getContext().getResources().getDisplayMetrics().density;
        return Math.round((float) dp * density);
    }
}