package com.overswayit.plesnisavezsrbije.networking

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by lazarristic on 2019-08-08.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
object LastModifiedApiService{
    private val interceptor = Interceptor { chain ->
        val url = chain.request().url().newBuilder().build()
        val request = chain.request().newBuilder().url(url).build()
        chain.proceed(request)
    }

    private val apiClient = OkHttpClient().newBuilder().addInterceptor(interceptor).build()

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder().client(apiClient)
                .baseUrl(Config.BASE_URL)
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    val lastModifiedApi: LastModifiedApiInterface = getRetrofit().create(LastModifiedApiInterface::class.java)
}