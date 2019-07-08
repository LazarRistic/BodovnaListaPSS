package com.overswayit.plesnisavezsrbije.viewmodels

import android.app.Application
import androidx.lifecycle.*

import com.overswayit.plesnisavezsrbije.models.News
import com.overswayit.plesnisavezsrbije.repository.NewsRepository

import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.overswayit.plesnisavezsrbije.database.AppDatabase
import com.overswayit.plesnisavezsrbije.database.FakeClubs
import com.overswayit.plesnisavezsrbije.database.FakeNews
import com.overswayit.plesnisavezsrbije.models.Club
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.collections.get

/**
 * Created by lazarristic on 18/02/2019.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
class NewsViewModel(application: Application) : AndroidViewModel(application) {
    private val newsRepository: NewsRepository = NewsRepository(application)
    private val observableNews = MediatorLiveData<PagedList<News>>()
    private var config: PagedList.Config = PagedList.Config.Builder().setPageSize(6).setEnablePlaceholders(true).build()

    init {
        viewModelScope.launch {
            insertNews()

            val news = newsRepository.getAllPaged(config)
            observableNews.addSource(news, observableNews::setValue)
        }
    }

    fun allNews(): LiveData<PagedList<News>> {
        return observableNews
    }

    private suspend fun insertNews() = withContext(Dispatchers.IO) {
        FakeNews.getAllNews().forEach {
            newsRepository.insertOrUpdate(it)
        }
    }
}
