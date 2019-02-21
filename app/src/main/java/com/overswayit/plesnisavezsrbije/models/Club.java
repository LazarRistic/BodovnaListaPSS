package com.overswayit.plesnisavezsrbije.models;

import java.util.ArrayList;

/**
 * Created by lazarristic on 19/02/2019.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
public class Club {
    public int id;
    public String logoUrl;
    public String name;
    public String town;
    public String address;
    public String contactName;
    public ArrayList<String> phoneNumbers = new ArrayList<>();
    public String email;
}
