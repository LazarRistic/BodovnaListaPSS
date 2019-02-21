package com.overswayit.plesnisavezsrbije.events;

/**
 * Created by lazarristic on 20/02/2019.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
public class Event<T> {
    public T content;

    public void post(T content) {
        try {
            Event<T> instance = this.getClass().newInstance();
            instance.content = content;
            EventBus.post(instance);
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
