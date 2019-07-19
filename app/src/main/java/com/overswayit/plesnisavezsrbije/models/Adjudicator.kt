package com.overswayit.plesnisavezsrbije.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.ArrayList

/**
 * Created by lazarristic on 21/02/2019.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
@Entity(tableName = "adjudicator", primaryKeys = ["id", "licenses_type"])
class Adjudicator(@ColumnInfo(name = "id") var id: String,
                  @ColumnInfo(name = "avatar_url") var avatarUrl: String,
                  @ColumnInfo(name = "name") var name: String,
                  @ColumnInfo(name = "licenses") var licenses: ArrayList<String>,
                  @ColumnInfo(name = "licenses_type") var licensesType: AdjudicatorLicensesType,
                  @ColumnInfo(name = "color") var color: Int)
