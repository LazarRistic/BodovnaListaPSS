package com.overswayit.plesnisavezsrbije.models

import androidx.room.ColumnInfo
import androidx.room.Entity

/**
 * Created by lazarristic on 2019-05-23.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
@Entity(primaryKeys = ["couple_id", "list_type", "dance_category", "dance_type", "age_category"], tableName = "point_list_item")
class PointListItem(@ColumnInfo(name = "dance_category") var danceCategory: DanceCategory)
    : CouplesListItem(listType = ListType.POINT)
