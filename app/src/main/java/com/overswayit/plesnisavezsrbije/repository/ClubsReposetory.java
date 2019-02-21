package com.overswayit.plesnisavezsrbije.repository;

import android.app.Application;
import android.os.Handler;

import com.overswayit.plesnisavezsrbije.models.Club;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by lazarristic on 19/02/2019.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
public class ClubsReposetory {
    private Application application;
    private CompositeDisposable disposable = new CompositeDisposable();
    private MutableLiveData<List<Club>> clubsLiveData = new MutableLiveData<>();

    public ClubsReposetory(Application application) {
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

        Club best = new Club();
        best.name = "Best";
        best.address = "Kružni Put Kijevo 21v";
        best.town = "Beograd";
        best.contactName = "Branislava Radovanovic";
        best.phoneNumbers.add("0605523289");
        best.phoneNumbers.add("0112337082");
        best.email = "bubarada70@gmail.com";
        best.logoUrl = "http://www.ples.co.rs/klubovi/logo/K01446.jpg";

        Club calypso = new Club();
        calypso.name = "Calypso";
        calypso.address = "Miloja Zakića 1/7";
        calypso.town = "Beograd";
        calypso.contactName = "Dragana Labudović";
        calypso.phoneNumbers.add("063/82 82 225");
        calypso.email = "pkcalypso.dragana@gmail.com";
        calypso.logoUrl = "http://www.ples.co.rs/klubovi/logo/K00066.jpg";

        List<Club> clubList = new ArrayList<>();
        clubList.add(best);

        Handler handler = new Handler();
        handler.postDelayed(() -> clubsLiveData.postValue(clubList), 1000);
        handler.postDelayed(() -> clubList.add(calypso), 3000);
        handler.postDelayed(() -> clubsLiveData.postValue(clubList), 5000);

    }

    public LiveData<List<Club>> getClubsLiveData() {
        return clubsLiveData;
    }
}
