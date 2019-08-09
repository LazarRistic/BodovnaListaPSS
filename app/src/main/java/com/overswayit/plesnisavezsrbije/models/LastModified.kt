package com.overswayit.plesnisavezsrbije.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by lazarristic on 2019-08-08.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
@Entity
data class LastModified(@PrimaryKey val name: String,
                        @ColumnInfo val date: String)