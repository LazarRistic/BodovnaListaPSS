package com.overswayit.plesnisavezsrbije.models

/**
 * Created by lazarristic on 2019-06-07.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
abstract class CouplesListItem(val listType: ListType) {
    var ageCategory: AgeCategory? = null
    var place: String? = null
    var points: String? = null
    var danceType: DanceType? = null
    var couple: Couple? = null
}