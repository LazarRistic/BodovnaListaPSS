package com.overswayit.plesnisavezsrbije.repository

import android.app.Application
import android.os.Handler
import android.util.Log

import com.overswayit.plesnisavezsrbije.models.News

import java.util.ArrayList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.overswayit.plesnisavezsrbije.database.AppDatabase
import com.overswayit.plesnisavezsrbije.database.FakeNews
import com.overswayit.plesnisavezsrbije.database.NewsDao
import com.overswayit.plesnisavezsrbije.models.Club
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.Dispatchers

/**
 * Created by lazarristic on 18/02/2019.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
class NewsRepository(private val application: Application) {
    private var newsDao: NewsDao = AppDatabase.invoke(application.applicationContext).newsDao()

    suspend fun getAll(): LiveData<List<News>> = liveData(Dispatchers.IO) {
        emitSource(newsDao.getAll())
    }

    fun getAllPaged(config: PagedList.Config): LiveData<PagedList<News>> = liveData(Dispatchers.IO) {
        emitSource(LivePagedListBuilder(newsDao.getAllPaged(), config).build())
    }

    fun insertOrUpdate(vararg news: News) {
        newsDao.deleteAndInsertAll(news.asList(), news.asList())
    }

    fun insert(vararg news: News) {
        news.forEach {
            newsDao.insertAll(it)
        }
    }
}
