package com.overswayit.plesnisavezsrbije.viewmodels;

import com.overswayit.plesnisavezsrbije.models.News;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * Created by lazarristic on 18/02/2019.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */

public class SingleNewsViewModel extends ViewModel {
    private MutableLiveData<String> title;
    private MutableLiveData<String> date;
    private MutableLiveData<String> content;

    public SingleNewsViewModel(News news) {
        this.title = new MutableLiveData<>();
        this.date = new MutableLiveData<>();
        this.content = new MutableLiveData<>();

        this.title.setValue(news.title);
        this.date.setValue(news.date);
        this.content.setValue(news.content);
    }

    public LiveData<String> getTitle() {
        return title;
    }

    public LiveData<String> getDate() {
        return date;
    }

    public LiveData<String> getContent() {
        return content;
    }
}
