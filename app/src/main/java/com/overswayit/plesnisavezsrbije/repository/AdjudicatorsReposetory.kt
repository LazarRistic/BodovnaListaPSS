package com.overswayit.plesnisavezsrbije.repository

import android.app.Application
import android.os.Handler
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.overswayit.plesnisavezsrbije.models.Adjudicator
import com.overswayit.plesnisavezsrbije.models.AdjudicatorLicensesType
import com.overswayit.plesnisavezsrbije.models.AdjudicatorLicensesType.LA_ST
import com.overswayit.plesnisavezsrbije.models.AdjudicatorLicensesType.MODERN
import java.util.*
import java.util.stream.Collectors

/**
 * Created by lazarristic on 21/02/2019.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
class AdjudicatorsReposetory(private val application: Application) {
    private val laStAdjudicatorsLiveData = MutableLiveData<List<Adjudicator>>()
    private val modernAdjudicatorsLiveData = MutableLiveData<List<Adjudicator>>()

    internal interface FakeAdjudicatorListener {
        fun onNewAdjudicator(adjudicators: List<Adjudicator>)
    }

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

        getFakeAdjudicators(object : FakeAdjudicatorListener {
            override fun onNewAdjudicator(adjudicators: List<Adjudicator>) {
                filterAdjudicators(adjudicators)
            }
        })
    }

    fun getAdjudicatorsLiveData(licensesType: AdjudicatorLicensesType): LiveData<List<Adjudicator>> {
        return when (licensesType) {
            MODERN -> modernAdjudicatorsLiveData
            LA_ST -> laStAdjudicatorsLiveData
        }
    }

    private fun filterAdjudicators(adjudicators: List<Adjudicator>) {
        laStAdjudicatorsLiveData.postValue(adjudicators.stream().filter { adjudicator -> adjudicator.licensesType == LA_ST }.collect(Collectors.toList()))
        modernAdjudicatorsLiveData.postValue(adjudicators.stream().filter { adjudicator -> adjudicator.licensesType == MODERN }.collect(Collectors.toList()))
    }

    private fun getFakeAdjudicators(fakeAdjudicatorListener: FakeAdjudicatorListener) {
        val wdsf = "WDSF međunarodna licenca"
        val wdsfChair = "WDSF međunarodna licenca za Glavnog sudiju"
        val serbiaChar = "Licenca za Glavnog sudiju"
        val aLicence = "A licenca"

        val nesa = Adjudicator()
        nesa.name = "Nenad Jeftić"
        nesa.addLicense(wdsf)
        nesa.addLicense(wdsfChair)
        nesa.addLicense(serbiaChar)
        nesa.avatarUrl = "http://www.ples.co.rs/sudije/foto/001.jpg"
        nesa.licensesType = LA_ST

        val buba = Adjudicator()
        buba.name = "Branislava Radovanović"
        buba.addLicense(wdsf)
        buba.addLicense(serbiaChar)
        buba.avatarUrl = "http://www.ples.co.rs/sudije/foto/002.jpg"
        buba.licensesType = LA_ST

        val bombica = Adjudicator()
        bombica.name = "Ivan Knežević"
        bombica.addLicense(aLicence)
        bombica.avatarUrl = "http://www.ples.co.rs/sudije/foto/058.jpg"
        bombica.licensesType = LA_ST

        val mile = Adjudicator()
        mile.name = "Ivan Mileusnić"
        mile.addLicense(aLicence)
        mile.avatarUrl = "http://www.ples.co.rs/sudije/foto/063.jpg"
        mile.licensesType = LA_ST

        val gaga = Adjudicator()
        gaga.name = "Dragana Labudović"
        gaga.addLicense(aLicence)
        gaga.avatarUrl = "http://www.ples.co.rs/sudije/foto/060.jpg"
        gaga.licensesType = MODERN

        val danijela = Adjudicator()
        danijela.name = "Danijela Sagić"
        danijela.addLicense(aLicence)
        danijela.avatarUrl = "http://www.ples.co.rs/sudije/foto/062.jpg"
        danijela.licensesType = MODERN

        val miodrag = Adjudicator()
        miodrag.name = "Miodrag Mičić"
        miodrag.addLicense(aLicence)
        miodrag.avatarUrl = "http://www.ples.co.rs/sudije/foto/043.jpg"
        miodrag.licensesType = MODERN

        val adjudicators = ArrayList<Adjudicator>()
        adjudicators.add(nesa)
        adjudicators.add(buba)
        adjudicators.add(bombica)
        adjudicators.add(gaga)
        adjudicators.add(miodrag)

        fakeAdjudicatorListener.onNewAdjudicator(adjudicators)

        val handler = Handler()
        handler.postDelayed({
            adjudicators.add(mile)
            adjudicators.add(danijela)
            fakeAdjudicatorListener.onNewAdjudicator(adjudicators)
        }, 3000)
    }
}
