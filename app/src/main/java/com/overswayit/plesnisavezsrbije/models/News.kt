package com.overswayit.plesnisavezsrbije.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

/**
 * Created by lazarristic on 18/02/2019.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
@Entity(tableName = "news", indices = [Index(value = ["title", "date" ], unique = true)])
class News(@PrimaryKey(autoGenerate = true) var id: Int,
           @ColumnInfo(name = "title") var title: String,
           @ColumnInfo(name = "date") var date: String,
           @ColumnInfo(name = "content") var content: String )
