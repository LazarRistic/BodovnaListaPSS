package com.overswayit.plesnisavezsrbije.utils

import com.google.i18n.phonenumbers.NumberParseException
import com.google.i18n.phonenumbers.PhoneNumberUtil
import com.google.i18n.phonenumbers.Phonenumber
import java.util.*

/**
 * Created by lazarristic on 19/02/2019.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
object PhoneUtil {

    fun isMobilePhone(phoneOriginal: String): Boolean {
        var phone = phoneOriginal
        val phoneUtil = PhoneNumberUtil.getInstance()

        if (phone.startsWith("0")) {
            phone = "+381" + phone.substring(1)
        }

        try {
            val phoneNumber = getPhoneNumber(phone)
            val phoneNumberType = phoneUtil.getNumberType(phoneNumber)

            return phoneNumberType == PhoneNumberUtil.PhoneNumberType.MOBILE
        } catch (ignored: NumberParseException) {
        }

        return false
    }

    fun isLandLine(phone: String): Boolean {
        val phoneUtil = PhoneNumberUtil.getInstance()

        try {
            val phoneNumber = getPhoneNumber(phone)
            val phoneNumberType = phoneUtil.getNumberType(phoneNumber)

            return phoneNumberType == PhoneNumberUtil.PhoneNumberType.FIXED_LINE
        } catch (ignored: NumberParseException) {
        }

        return false
    }

    @Throws(NumberParseException::class)
    private fun getPhoneNumber(phone: String): Phonenumber.PhoneNumber {
        val phoneUtil = PhoneNumberUtil.getInstance()

        return phoneUtil.parse(phone, Locale.getDefault().country)
    }
}
