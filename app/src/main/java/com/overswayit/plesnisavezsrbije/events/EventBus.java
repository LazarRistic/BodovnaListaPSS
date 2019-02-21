package com.overswayit.plesnisavezsrbije.events;

import android.os.Handler;
import android.os.Looper;

import com.squareup.otto.Bus;

/**
 * Created by lazarristic on 20/02/2019.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
public class EventBus {
    private static final Bus bus = new Bus();
    private static final Handler handler = new Handler(Looper.getMainLooper());

    public static void register(Object subscriber) {
        bus.register(subscriber);
    }

    public static void unregister(Object subscriber) {
        bus.unregister(subscriber);
    }

    public static void post(final Event event) {
        handler.post(() -> bus.post(event));
    }
}
