package com.overswayit.plesnisavezsrbije.models;

import android.util.Pair;

import com.overswayit.plesnisavezsrbije.R;

import java.util.ArrayList;

/**
 * Created by lazarristic on 18/04/2019.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
public class AddressClubContact extends ClubContact {

    public AddressClubContact(String address) {
        super(address);

        Pair<String, Integer> pair = new Pair<>(super.contact, R.drawable.ic_message);
        ArrayList<Pair<String, Integer>> arrayList = new ArrayList<>();
        arrayList.add(pair);

        super.contacts = arrayList;
        super.type = ClubContactType.ADDRESS;
    }
}
