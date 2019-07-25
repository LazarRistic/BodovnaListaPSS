package com.overswayit.plesnisavezsrbije.viewmodels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.overswayit.plesnisavezsrbije.models.FederationDanceType

/**
 * Created by lazarristic on 2019-07-25.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
class CompetitionsEventsViewModelFactory(private val danceType: FederationDanceType, private val application: Application) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when (danceType) {
            FederationDanceType.MODERN -> ModernAdjudicatorsViewModel(application) as T
            FederationDanceType.LA_ST -> LaStAdjudicatorsViewModel(application) as T
        }
    }
}