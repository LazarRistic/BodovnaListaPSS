package com.overswayit.plesnisavezsrbije.networking

import retrofit2.Response
import retrofit2.http.GET

/**
 * Created by lazarristic on 2019-08-08.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
interface LastModifiedApiInterface {
    @GET("last_modified/")
    suspend fun getLastModifiedDate(): Response<Any>
}