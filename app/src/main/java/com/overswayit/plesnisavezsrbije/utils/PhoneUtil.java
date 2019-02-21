package com.overswayit.plesnisavezsrbije.utils;

import android.text.TextUtils;

/**
 * Created by lazarristic on 19/02/2019.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
public class PhoneUtil {

    public static boolean isMobilePhone(String phoneNumber) {
        if (!TextUtils.isEmpty(phoneNumber)) {
            if (phoneNumber.startsWith("+381")) {
                phoneNumber = phoneNumber.replace("+381", "0");
            } else if (phoneNumber.startsWith("381")) {
                phoneNumber = phoneNumber.replace("381", "0");
            } else if (phoneNumber.startsWith("00381")) {
                phoneNumber = phoneNumber.replace("00381", "0");
            }

            return phoneNumber.startsWith("06");
        }

        return false;
    }
}
