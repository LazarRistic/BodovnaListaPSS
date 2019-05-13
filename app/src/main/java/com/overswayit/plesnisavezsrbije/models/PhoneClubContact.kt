package com.overswayit.plesnisavezsrbije.models

import android.util.Pair

import com.overswayit.plesnisavezsrbije.R
import com.overswayit.plesnisavezsrbije.utils.PhoneUtil

import java.util.ArrayList

/**
 * Created by lazarristic on 18/04/2019.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
class PhoneClubContact(phone: String) : ClubContact(phone) {

    init {
        if (PhoneUtil.isMobilePhone(phone)) {
            createMobilePhoneClubContact()
        } else {
            createLandLinePhoneClubContact()
        }
    }

    private fun createMobilePhoneClubContact() {
        val call = Pair<String?, Int>(super.contact, R.drawable.ic_local_phone)
        val message = Pair<String?, Int>(super.contact, R.drawable.ic_message)
        val arrayList = ArrayList<Pair<String?, Int>>()
        arrayList.add(call)
        arrayList.add(message)

        super.contacts = arrayList
        super.type = ClubContactType.MOBILE
    }

    private fun createLandLinePhoneClubContact() {
        val call = Pair<String?, Int>(super.contact, R.drawable.ic_local_phone)
        val arrayList = ArrayList<Pair<String?, Int>>()
        arrayList.add(call)

        super.contacts = arrayList
        super.type = ClubContactType.LAND_LINE
    }
}
