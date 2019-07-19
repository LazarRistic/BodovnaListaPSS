package com.overswayit.plesnisavezsrbije.networking

import retrofit2.Response
import retrofit2.http.GET

/**
 * Created by lazarristic on 2019-07-11.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
interface ClubsApiInterface {
    @GET("clubs/")
    suspend fun getClubs(): Response<Any>
}