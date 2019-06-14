package com.overswayit.plesnisavezsrbije.viewmodels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.overswayit.plesnisavezsrbije.models.RatingListItem

/**
 * Created by lazarristic on 2019-06-13.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
class CoupleRatingListViewModelFactory(private val application: Application, private val latin: RatingListItem, private val standard: RatingListItem, private val combined: RatingListItem) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CoupleRatingListViewModel(application, latin, standard, combined) as T
    }
}