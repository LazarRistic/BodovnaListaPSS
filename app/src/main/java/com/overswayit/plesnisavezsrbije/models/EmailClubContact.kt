package com.overswayit.plesnisavezsrbije.models

import android.util.Pair

import com.overswayit.plesnisavezsrbije.R

import java.util.ArrayList

/**
 * Created by lazarristic on 18/04/2019.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
class EmailClubContact(email: String?) : ClubContact(email) {

    init {
        val pair = Pair<String?, Int>(super.contact, R.drawable.ic_email)
        val arrayList = ArrayList<Pair<String?, Int>>()
        arrayList.add(pair)

        super.contacts = arrayList
        super.type = ClubContactType.EMAIL
    }
}
