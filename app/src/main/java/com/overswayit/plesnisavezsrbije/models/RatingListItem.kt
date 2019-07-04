package com.overswayit.plesnisavezsrbije.models

import androidx.room.Entity

/**
 * Created by lazarristic on 2019-06-03.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
@Entity(primaryKeys = ["couple_id", "list_type", "dance_type", "age_category"], tableName = "rating_list_item")
class RatingListItem : CouplesListItem(listType = ListType.RATING)