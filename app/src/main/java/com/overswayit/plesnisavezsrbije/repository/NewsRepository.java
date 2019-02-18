package com.overswayit.plesnisavezsrbije.repository;

import android.app.Application;
import android.os.Handler;

import com.overswayit.plesnisavezsrbije.models.News;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by lazarristic on 18/02/2019.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
public class NewsRepository {

    private Application application;
    private CompositeDisposable disposable = new CompositeDisposable();
    private MutableLiveData<List<News>> newsLiveData = new MutableLiveData<>();

//    private NewsAppDatabes newsAppDatabes;

    public NewsRepository(Application application) {
        this.application = application;

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

        News news = new News();
        news.title = "Title";
        news.date = "02. februar 2019.";
        news.content = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.";

        News news2 = new News();
        news2.title = "Title 22";
        news2.date = "02. maj 2019.";
        news2.content = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.";

        List<News> newsList = new ArrayList<>();
        newsList.add(news);

        Handler handler = new Handler();
        handler.postDelayed(() -> newsLiveData.postValue(newsList), 5000);
        handler.postDelayed(() -> newsList.add(news2), 7000);
        handler.postDelayed(() -> newsLiveData.postValue(newsList), 8000);

    }

    public LiveData<List<News>> getNewsLiveData() {
        return newsLiveData;
    }
}
