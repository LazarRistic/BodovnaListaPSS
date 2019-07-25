package com.overswayit.plesnisavezsrbije.models

import androidx.room.ColumnInfo
import androidx.room.Embedded

/**
 * Created by lazarristic on 2019-06-07.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
abstract class CouplesListItem(@ColumnInfo(name = "list_type") var listType: ListType) {
    @ColumnInfo(name = "age_category") lateinit var  ageCategory: AgeCategory
    @ColumnInfo(name = "place") var  place: Int = 0
    @ColumnInfo(name = "points") var  points: Int = 0
    @ColumnInfo(name = "dance_type") lateinit var danceType: DanceType
    @Embedded(prefix = "couple_") lateinit var  couple: Couple
}