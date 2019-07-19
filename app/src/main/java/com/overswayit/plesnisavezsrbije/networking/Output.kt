package com.overswayit.plesnisavezsrbije.networking

/**
 * Created by lazarristic on 2019-07-11.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
sealed class Output<out T: Any> {
    data class Success<out T: Any>(val output : T) : Output<T>()
    data class Error(val exception: Exception) : Output<Nothing>()
}