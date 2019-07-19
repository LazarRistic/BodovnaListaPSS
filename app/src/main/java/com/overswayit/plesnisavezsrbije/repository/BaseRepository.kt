package com.overswayit.plesnisavezsrbije.repository

import android.util.Log
import com.overswayit.plesnisavezsrbije.networking.Output
import retrofit2.Response
import java.io.IOException

/**
 * Created by lazarristic on 2019-07-11.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
open class BaseRepository {
    suspend fun <T: Any> safeApiCall(call : suspend() -> Response<T>, error: String): T?{
        val result = clubApiOutput(call, error)
        var output : T? = null
        when(result) {
            is Output.Success -> output = result.output
            is Output.Error -> Log.e("LAZA Error", "The $error and the ${result.exception}")
        }

        return output
    }

    private suspend fun<T : Any> clubApiOutput(call: suspend() -> Response<T>, error: String): Output<T>? {
        val response = call.invoke()
        return if (response.isSuccessful) {
            Output.Success(response.body()!!)
        } else {
            Output.Error(IOException("Oops .. Something went wrong due to $error"))
        }
    }
}