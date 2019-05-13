package com.overswayit.plesnisavezsrbije.events

import android.os.Handler
import android.os.Looper

import com.squareup.otto.Bus

/**
 * Created by lazarristic on 20/02/2019.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
object EventBus {
    private val bus = Bus()
    private val handler = Handler(Looper.getMainLooper())

    fun register(subscriber: Any) {
        bus.register(subscriber)
    }

    fun unregister(subscriber: Any) {
        bus.unregister(subscriber)
    }

    fun post(event: Event<*>) {
        handler.post { bus.post(event) }
    }
}
