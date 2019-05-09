package com.overswayit.plesnisavezsrbije.repository;

import android.app.Application;
import android.os.Handler;

import com.overswayit.plesnisavezsrbije.models.Adjudicator;
import com.overswayit.plesnisavezsrbije.models.AdjudicatorLicensesType;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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

    interface FakeAdjudicatorListener {
        void onNewAdjudicator(List<Adjudicator> adjudicators);
    }

    public AdjudicatorsReposetory(Application application, AdjudicatorLicensesType licensesType) {
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

        getFakeAdjudicators(adjudicators -> filterAdjudicators(licensesType, adjudicators));
    }

    public LiveData<List<Adjudicator>> getAdjudicatorsLiveData() {
        return adjudicatorsLiveData;
    }

    private void filterAdjudicators(AdjudicatorLicensesType licensesType, List<Adjudicator> adjudicators) {
        adjudicatorsLiveData.postValue(adjudicators.stream().filter(adjudicator -> adjudicator.licensesType == licensesType).collect(Collectors.toList()));
    }

    private void getFakeAdjudicators(FakeAdjudicatorListener fakeAdjudicatorListener) {
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
        nesa.licensesType = AdjudicatorLicensesType.LA_ST;

        Adjudicator buba = new Adjudicator();
        buba.name = "Branislava Radovanović";
        buba.licenses.add(wdsf);
        buba.licenses.add(serbiaChar);
        buba.avatarUrl = "http://www.ples.co.rs/sudije/foto/002.jpg";
        buba.licensesType = AdjudicatorLicensesType.LA_ST;

        Adjudicator bombica = new Adjudicator();
        bombica.name = "Ivan Knežević";
        bombica.licenses.add(aLicence);
        bombica.avatarUrl = "http://www.ples.co.rs/sudije/foto/058.jpg";
        bombica.licensesType = AdjudicatorLicensesType.LA_ST;

        Adjudicator mile = new Adjudicator();
        mile.name = "Ivan Mileusnić\t";
        mile.licenses.add(aLicence);
        mile.avatarUrl = "http://www.ples.co.rs/sudije/foto/063.jpg";
        mile.licensesType = AdjudicatorLicensesType.LA_ST;

        Adjudicator gaga = new Adjudicator();
        gaga.name = "Dragana Labudovic";
        gaga.licenses.add(aLicence);
        gaga.avatarUrl = "http://www.ples.co.rs/sudije/foto/060.jpg";
        gaga.licensesType = AdjudicatorLicensesType.MODERN;

        Adjudicator danijela = new Adjudicator();
        danijela.name = "Danijela Sagic";
        danijela.licenses.add(aLicence);
        danijela.avatarUrl = "http://www.ples.co.rs/sudije/foto/062.jpg";
        danijela.licensesType = AdjudicatorLicensesType.MODERN;

        Adjudicator miodrag = new Adjudicator();
        miodrag.name = "Miodrag Micic";
        miodrag.licenses.add(aLicence);
        miodrag.avatarUrl = "http://www.ples.co.rs/sudije/foto/043.jpg";
        miodrag.licensesType = AdjudicatorLicensesType.MODERN;

        List<Adjudicator> adjudicators = new ArrayList<>();
        adjudicators.add(nesa);
        adjudicators.add(buba);
        adjudicators.add(bombica);
        adjudicators.add(gaga);
        adjudicators.add(miodrag);

        fakeAdjudicatorListener.onNewAdjudicator(adjudicators);

        Handler handler = new Handler();
        handler.postDelayed(() -> {
            adjudicators.add(mile);
            adjudicators.add(danijela);
            fakeAdjudicatorListener.onNewAdjudicator(adjudicators);
        }, 3000);
    }
}
