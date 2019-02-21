package com.overswayit.plesnisavezsrbije.repository;

import android.app.Application;

import com.overswayit.plesnisavezsrbije.models.Adjudicator;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by lazarristic on 21/02/2019.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
public class AdjudicatorsReposetory {
    private Application application;
    private CompositeDisposable disposable = new CompositeDisposable();
    private MutableLiveData<List<Adjudicator>> adjudicatorsLiveData = new MutableLiveData<>();

    public AdjudicatorsReposetory(Application application) {
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

        String wdsf = "WDSF međunarodna licenca";
        String wdsfChair = "WDSF međunarodna licenca za Glavnog sudiju";
        String serbiaChar = "Licenca za Glavnog sudiju";
        String aLicence = "A licenca";

        Adjudicator nesa = new Adjudicator();
        nesa.name = "Nenad Jeftić";
        nesa.licenses.add(wdsf);
        nesa.licenses.add(wdsfChair);
        nesa.licenses.add(serbiaChar);
        nesa.avatarUrl = "http://www.ples.co.rs/sudije/foto/001.jpg";

        Adjudicator buba = new Adjudicator();
        buba.name = "Branislava Radovanović";
        buba.licenses.add(wdsf);
        buba.licenses.add(serbiaChar);
        buba.avatarUrl = "http://www.ples.co.rs/sudije/foto/002.jpg";

        Adjudicator bombica = new Adjudicator();
        bombica.name = "Ivan Knežević";
        bombica.licenses.add(aLicence);
        bombica.avatarUrl = "http://www.ples.co.rs/sudije/foto/058.jpg";

        Adjudicator mile = new Adjudicator();
        mile.name = "Ivan Mileusnić\t";
        mile.licenses.add(aLicence);
        mile.avatarUrl = "http://www.ples.co.rs/sudije/foto/063.jpg";

        List<Adjudicator> adjudicators = new ArrayList<>();
        adjudicators.add(nesa);
        adjudicators.add(buba);
        adjudicators.add(bombica);
        adjudicators.add(mile);

        adjudicatorsLiveData.setValue(adjudicators);
        adjudicatorsLiveData.postValue(adjudicators);
    }

    public LiveData<List<Adjudicator>> getAdjudicatorsLiveData() {
        return adjudicatorsLiveData;
    }
}
