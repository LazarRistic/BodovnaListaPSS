package com.overswayit.plesnisavezsrbije.viewmodels

import android.app.Application

import com.overswayit.plesnisavezsrbije.models.News
import com.overswayit.plesnisavezsrbije.repository.NewsRepository

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

/**
 * Created by lazarristic on 18/02/2019.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
class NewsViewModel(application: Application) : AndroidViewModel(application) {
    private val newsRepository: NewsRepository = NewsRepository(application)

    val allNews: LiveData<List<News>>
        get() = newsRepository.getNewsLiveData()
}
