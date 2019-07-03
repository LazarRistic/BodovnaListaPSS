package com.overswayit.plesnisavezsrbije.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

/**
 * Created by lazarristic on 19/02/2019.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
@Entity
data class Club(@PrimaryKey var id: Int,
                @ColumnInfo(name = "logo_url") var logoUrl: String,
                @ColumnInfo(name = "name") var name: String,
                @ColumnInfo(name = "town") var town: String,
                @ColumnInfo(name = "address") var address: String,
                @ColumnInfo(name = "contact_name") var contactName: String,
                @ColumnInfo(name = "phone_numbers") var phoneNumbers: ArrayList<String>,
                @ColumnInfo(name = "email") var email: String)