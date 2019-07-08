package com.overswayit.plesnisavezsrbije.viewmodels

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.overswayit.plesnisavezsrbije.database.fake.FakeAdjudicators
import com.overswayit.plesnisavezsrbije.models.Adjudicator
import com.overswayit.plesnisavezsrbije.models.AdjudicatorLicensesType
import com.overswayit.plesnisavezsrbije.repository.AdjudicatorsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * Created by lazarristic on 21/02/2019.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
open class AdjudicatorsViewModel internal constructor(private val licensesType: AdjudicatorLicensesType, application: Application) : ViewModel() {
    private val adjudicatorsRepository: AdjudicatorsRepository = AdjudicatorsRepository(application)
    private val observableAdjudicator = MediatorLiveData<List<Adjudicator>>()

    init {
        viewModelScope.launch {
            insertFakeAdjudicators()

            val adjudicators = adjudicatorsRepository.getAllWithLicensesType(licensesType)
            observableAdjudicator.addSource(adjudicators, observableAdjudicator::setValue)
        }
    }

    private suspend fun insertFakeAdjudicators() = withContext(Dispatchers.IO) {
        adjudicatorsRepository.insertOrUpdate(FakeAdjudicators.getFakeAdjudicators())
    }

    fun getAll(): LiveData<List<Adjudicator>> {
        return observableAdjudicator
    }

}


