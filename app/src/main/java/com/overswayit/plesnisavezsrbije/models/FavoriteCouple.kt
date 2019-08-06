package com.overswayit.plesnisavezsrbije.models

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by lazarristic on 2019-06-24.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
@Entity
data class FavoriteCouple(@PrimaryKey(autoGenerate = true) val id: Int = 0,
                          @Embedded(prefix = "couple_") val couple: Couple,
                          @ColumnInfo(name = "favorite") var favorite: Boolean = false)