package com.overswayit.plesnisavezsrbije.repository

import android.app.Application
import android.os.Handler

import com.overswayit.plesnisavezsrbije.models.Club

import java.util.ArrayList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.overswayit.plesnisavezsrbije.database.FakeClubs
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by lazarristic on 23/04/2019.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
class ClubRepository(id: Int, private val application: Application) {
    private val disposable = CompositeDisposable()
    val clubLiveData = MutableLiveData<Club>()

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


        val handler = Handler()

        FakeClubs.getAllCLubs().forEach {
            handler.postDelayed({ clubLiveData.postValue(it) }, 300)
        }
    }

    fun getClubLiveData(): LiveData<Club> {
        return clubLiveData
    }
}
