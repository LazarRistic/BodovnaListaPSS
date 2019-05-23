package com.overswayit.plesnisavezsrbije.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.overswayit.plesnisavezsrbije.models.Adjudicator
import com.overswayit.plesnisavezsrbije.models.AdjudicatorLicensesType
import com.overswayit.plesnisavezsrbije.repository.AdjudicatorsReposetory

/**
 * Created by lazarristic on 21/02/2019.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
open class AdjudicatorsViewModel internal constructor(private val licensesType: AdjudicatorLicensesType) : ViewModel() {

    private val adjudicatorsReposetory: AdjudicatorsReposetory = AdjudicatorsReposetory()

    val allAdjudicators: LiveData<List<Adjudicator>>
        get() = adjudicatorsReposetory.getAdjudicatorsLiveData(licensesType)

}


