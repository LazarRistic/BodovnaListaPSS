package com.overswayit.plesnisavezsrbije.networking

import retrofit2.Response
import retrofit2.http.GET

/**
 * Created by lazarristic on 2019-07-12.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
interface AdjudicatorsApiInterface {
    @GET("adjudicators/")
    suspend fun getAdjudicators(): Response<Any>
}