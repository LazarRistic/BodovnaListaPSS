package com.overswayit.plesnisavezsrbije.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.overswayit.plesnisavezsrbije.models.Couple
import com.overswayit.plesnisavezsrbije.models.PointListItem
import com.overswayit.plesnisavezsrbije.models.RatingListItem
import com.overswayit.plesnisavezsrbije.repository.ListRepository

/**
 * Created by lazarristic on 2019-05-23.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
class ListViewModel(application: Application) : AndroidViewModel(application) {
    private val listRepository: ListRepository = ListRepository(application)

    val laPointListCouples: LiveData<List<PointListItem>>
        get() = listRepository.getLaPointListCouples()

    val stPointListCouples: LiveData<List<PointListItem>>
        get() = listRepository.getStPointListCouples()

    val laRatingListCouples: LiveData<List<RatingListItem>>
        get() = listRepository.getLaRatingListCouples()

    val stRatingListCouples: LiveData<List<RatingListItem>>
        get() = listRepository.getStRatingListCouples()

    val kmRatingListCouples: LiveData<List<RatingListItem>>
        get() = listRepository.getKmRatingListCouples()
}