package com.overswayit.plesnisavezsrbije.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.overswayit.plesnisavezsrbije.parsers.server.ClubParser
import com.overswayit.plesnisavezsrbije.services.JsonService
import java.util.*

/**
 * Created by lazarristic on 19/02/2019.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
@Entity
data class Club(@PrimaryKey var id: String,
                @ColumnInfo(name = "logo_url") var logoUrl: String,
                @ColumnInfo(name = "name") var name: String,
                @ColumnInfo(name = "town") var town: String,
                @ColumnInfo(name = "country") var country: String,
                @ColumnInfo(name = "address") var address: String,
                @ColumnInfo(name = "web") var web: String,
                @ColumnInfo(name = "contact_name") var contactName: String,
                @ColumnInfo(name = "phone_numbers") var phoneNumbers: ArrayList<String>,
                @ColumnInfo(name = "email") var email: String)