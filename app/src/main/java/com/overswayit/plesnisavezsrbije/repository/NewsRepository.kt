package com.overswayit.plesnisavezsrbije.repository

import android.app.Application
import android.os.Handler

import com.overswayit.plesnisavezsrbije.models.News

import java.util.ArrayList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.overswayit.plesnisavezsrbije.database.FakeNews
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


        newsLiveData.postValue(FakeNews.getAllNews())
    }

    fun getNewsLiveData(): LiveData<List<News>> {
        return newsLiveData
    }
}
