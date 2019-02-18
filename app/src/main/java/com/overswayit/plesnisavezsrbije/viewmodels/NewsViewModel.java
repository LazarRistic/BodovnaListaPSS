package com.overswayit.plesnisavezsrbije.viewmodels;

import android.app.Application;

import com.overswayit.plesnisavezsrbije.models.News;
import com.overswayit.plesnisavezsrbije.repository.NewsRepository;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

/**
 * Created by lazarristic on 18/02/2019.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
public class NewsViewModel extends AndroidViewModel {
    private NewsRepository newsRepository;

    public NewsViewModel(@NotNull Application application) {
        super(application);

        newsRepository = new NewsRepository(application);
    }

    public LiveData<List<News>> getAllNews() {
        return newsRepository.getNewsLiveData();
    }
}
