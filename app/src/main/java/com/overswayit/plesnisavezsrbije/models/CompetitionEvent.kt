package com.overswayit.plesnisavezsrbije.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by lazarristic on 2019-07-24.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
@Entity
class CompetitionEvent(@PrimaryKey var id: Int,
                       @ColumnInfo(name = "date") var date: ArrayList<String>,
                       @ColumnInfo(name = "organiser") var organiser: String,
                       @ColumnInfo(name = "town") var town: String,
                       @ColumnInfo(name = "gym") var gym: String? = "",
                       @ColumnInfo(name = "competitions") var competition: ArrayList<Competition>)