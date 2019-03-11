package com.overswayit.plesnisavezsrbije.models;

/**
 * Created by lazarristic on 25/02/2019.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
public enum AgeCategory {
    JUVENILE("PIO"),
    JUNIOR_I("MLO"),
    JUNIOR_II("OML"),
    YOUTH("STO"),
    ADULT("SEN"),
    SENIOR("STS");

    private String val;

    AgeCategory(String val) {
        this.val = val;
    }

    public static AgeCategory fromString(String category) {
        for (AgeCategory ageCategory : AgeCategory.values()) {
            if (ageCategory.val.equals(category)) {
                return ageCategory;
            }
        }

        return null;
    }

    public String asString() {
        return this.val;
    }
}
