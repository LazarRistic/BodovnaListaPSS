package com.overswayit.plesnisavezsrbije.viewmodels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.overswayit.plesnisavezsrbije.models.Couple
import com.overswayit.plesnisavezsrbije.models.PointListItem

/**
 * Created by lazarristic on 2019-06-14.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
class CoupleInfoViewModelFactory(private val application: Application, private val coupleId: String) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CoupleInfoViewModel(application, coupleId) as T
    }
}