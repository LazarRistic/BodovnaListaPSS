package com.overswayit.plesnisavezsrbije.models;

import com.overswayit.tuple.Quadruple;
import com.overswayit.tuple.Triple;

/**
 * Created by lazarristic on 25/02/2019.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
public class Couple {
    public String id;
    public String nameMale;
    public String nameFemale;
    public Club club;

    public Triple<AgeCategory, DanceCategory, String> pointListLA;
    public Triple<AgeCategory, DanceCategory, String> pointListST;
    public Quadruple<AgeCategory, DanceCategory, String, String> ratingListLA;
    public Quadruple<AgeCategory, DanceCategory, String, String> ratingListST;
    public Quadruple<AgeCategory, DanceCategory, String, String> ratingListKM;

}
