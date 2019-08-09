package com.overswayit.plesnisavezsrbije.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by lazarristic on 2019-08-06.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
@Entity
data class Filter(@PrimaryKey(autoGenerate = true) val id: Int = 0,
              @ColumnInfo(name = "filter_name") val filterName: String,
              @ColumnInfo(name = "active") var active: Boolean = false)