package com.overswayit.plesnisavezsrbije.models

import com.overswayit.tuple.Quadruple
import com.overswayit.tuple.Triple

/**
 * Created by lazarristic on 25/02/2019.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
class Couple {
    var id: String? = null
    var nameMale: String? = null
    var nameFemale: String? = null
    var club: Club? = null

    var pointListLA: Triple<AgeCategory, DanceCategory, String>? = null
    var pointListST: Triple<AgeCategory, DanceCategory, String>? = null
    var ratingListLA: Quadruple<AgeCategory, DanceCategory, String, String>? = null
    var ratingListST: Quadruple<AgeCategory, DanceCategory, String, String>? = null
    var ratingListKM: Quadruple<AgeCategory, DanceCategory, String, String>? = null
}
