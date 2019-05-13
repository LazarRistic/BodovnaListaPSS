package com.overswayit.plesnisavezsrbije.models;

import android.util.Pair;

import com.overswayit.plesnisavezsrbije.R;

import java.util.ArrayList;

/**
 * Created by lazarristic on 18/04/2019.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
public class EmailClubContact extends ClubContact {

    public EmailClubContact(String email) {
        super(email);

        Pair<String, Integer> pair = new Pair<>(super.getContact(), R.drawable.ic_email);
        ArrayList<Pair<String, Integer>> arrayList = new ArrayList<>();
        arrayList.add(pair);

        super.setContacts(arrayList);
        super.setType(ClubContactType.EMAIL);
    }
}
