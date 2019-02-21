package com.overswayit.plesnisavezsrbije.viewmodels;

import com.overswayit.plesnisavezsrbije.models.Adjudicator;

import org.jetbrains.annotations.NotNull;

/**
 * Created by lazarristic on 21/02/2019.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
public class AdjudicatorViewModel {

    private Adjudicator adjudicator;

    public AdjudicatorViewModel(@NotNull Adjudicator adjudicator) {
        this.adjudicator = adjudicator;
    }

    public String getName() {
        return adjudicator.name;
    }

    public String getLicenses() {
        StringBuilder licenses = new StringBuilder();

        for (String license : adjudicator.licenses) {
            licenses.append(license).append(System.getProperty("line.separator"));
        }

        return licenses.toString();
    }

    public String getAvatarUri() {
        return adjudicator.avatarUrl;
    }
}
