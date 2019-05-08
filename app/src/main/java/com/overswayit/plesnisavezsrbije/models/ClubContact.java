package com.overswayit.plesnisavezsrbije.models;

import android.util.Pair;

import java.util.ArrayList;

/**
 * Created by lazarristic on 18/04/2019.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
public abstract class ClubContact {

    public String contact;

    ClubContact(String contact) {
        this.contact = contact;
    }

    public ClubContactType type;
    public ArrayList<Pair<String, Integer>> contacts;

}
