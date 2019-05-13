package com.overswayit.plesnisavezsrbije.events

/**
 * Created by lazarristic on 20/02/2019.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
open class Event<T> {
    var content: T? = null

    fun post(content: T) {
        try {
            val instance = this.javaClass.newInstance()
            instance.content = content
            EventBus.post(instance)
        } catch (e: InstantiationException) {
            e.printStackTrace()
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        }

    }
}
