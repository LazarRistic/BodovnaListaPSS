package com.overswayit.plesnisavezsrbije.models

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by lazarristic on 25/02/2019.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
@Entity
class Couple(@PrimaryKey var id: String,
             @ColumnInfo(name = "name_male") var nameMale: String,
             @ColumnInfo(name = "name_female") var nameFemale: String,
             @Embedded(prefix = "club_") var club: Club)
