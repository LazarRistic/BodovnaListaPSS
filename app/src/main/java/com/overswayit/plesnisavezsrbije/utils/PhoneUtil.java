package com.overswayit.plesnisavezsrbije.utils;

import android.text.TextUtils;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;

import java.util.Locale;

/**
 * Created by lazarristic on 19/02/2019.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
public class PhoneUtil {

    public static boolean isMobilePhone(String phone) {
        PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();

        if (phone.startsWith("0")) {
            phone = "+381" + phone.substring(1);
        }

        try {
            Phonenumber.PhoneNumber phoneNumber = getPhoneNumber(phone);
            PhoneNumberUtil.PhoneNumberType phoneNumberType = phoneUtil.getNumberType(phoneNumber);

            return phoneNumberType == PhoneNumberUtil.PhoneNumberType.MOBILE;
        } catch (NumberParseException ignored) {}

        return false;
    }

    public static boolean isLandLine(String phone) {
        PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();

        try {
            Phonenumber.PhoneNumber phoneNumber = getPhoneNumber(phone);
            PhoneNumberUtil.PhoneNumberType phoneNumberType = phoneUtil.getNumberType(phoneNumber);

            return phoneNumberType == PhoneNumberUtil.PhoneNumberType.FIXED_LINE;
        } catch (NumberParseException ignored) {}

        return false;
    }

    private static Phonenumber.PhoneNumber getPhoneNumber(String phone) throws NumberParseException {
        PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();

        return phoneUtil.parse(phone, Locale.getDefault().getCountry());
    }
}
