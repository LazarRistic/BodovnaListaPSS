package com.overswayit.plesnisavezsrbije.viewmodels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

import com.overswayit.plesnisavezsrbije.models.AdjudicatorLicensesType

/**
 * Created by lazarristic on 2019-05-09.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
class AdjudicatorsViewModelFactory(private val application: Application, private val licensesType: AdjudicatorLicensesType) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when (licensesType) {
            AdjudicatorLicensesType.MODERN -> ModernAdjudicatorsViewModel(application) as T
            AdjudicatorLicensesType.LA_ST -> LaStAdjudicatorsViewModel(application) as T
        }
    }
}
