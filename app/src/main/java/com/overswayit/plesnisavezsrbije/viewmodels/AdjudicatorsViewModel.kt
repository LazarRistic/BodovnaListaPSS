package com.overswayit.plesnisavezsrbije.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.overswayit.plesnisavezsrbije.models.Adjudicator
import com.overswayit.plesnisavezsrbije.models.AdjudicatorLicensesType
import com.overswayit.plesnisavezsrbije.repository.AdjudicatorsReposetory

/**
 * Created by lazarristic on 21/02/2019.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
open class AdjudicatorsViewModel internal constructor(application: Application, private val licensesType: AdjudicatorLicensesType) : AndroidViewModel(application) {

    private val adjudicatorsReposetory: AdjudicatorsReposetory = AdjudicatorsReposetory(application)

    val allAdjudicators: LiveData<List<Adjudicator>>
        get() = adjudicatorsReposetory.getAdjudicatorsLiveData(licensesType)

}


