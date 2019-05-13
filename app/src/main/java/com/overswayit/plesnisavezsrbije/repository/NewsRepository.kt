package com.overswayit.plesnisavezsrbije.repository

import android.app.Application
import android.os.Handler

import com.overswayit.plesnisavezsrbije.models.News

import java.util.ArrayList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by lazarristic on 18/02/2019.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
class NewsRepository
//    private NewsAppDatabes newsAppDatabes;

(private val application: Application) {
    private val disposable = CompositeDisposable()
    private val newsLiveData = MutableLiveData<List<News>>()

    init {

        //        ToDo: Create Instance of Database
        //        contactsAppDatabase= Room.databaseBuilder(application.getApplicationContext(),ContactsAppDatabase.class,"ContactDB").build();

        //        ToDo: Create Event
        //        disposable.add(contactsAppDatabase.getContactDAO().getContacts()
        //                .subscribeOn(Schedulers.computation())
        //                .observeOn(AndroidSchedulers.mainThread())
        //                .subscribe(new Consumer<List<Contact>>() {
        //                               @Override
        //                               public void accept(List<Contact> contacts) throws Exception {
        //
        //                                   contactsLiveData.postValue(contacts);
        //
        //
        //                               }
        //                           }, new Consumer<Throwable>() {
        //                               @Override
        //                               public void accept(Throwable throwable) throws Exception {
        //
        //
        //                               }
        //                           }
        //                )
        //        );

        val news = News()
        news.title = "Title"
        news.date = "02. februar 2019."
        news.content = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum."

        val news2 = News()
        news2.title = "Title 22"
        news2.date = "02. maj 2019."
        news2.content = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum."

        val newsList = ArrayList<News>()
        newsList.add(news)
        newsLiveData.postValue(newsList)

        val handler = Handler()
        handler.postDelayed({ newsList.add(news2) }, 3000)
        handler.postDelayed({ newsLiveData.postValue(newsList) }, 3500)
    }

    fun getNewsLiveData(): LiveData<List<News>> {
        return newsLiveData
    }
}
