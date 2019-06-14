package com.overswayit.plesnisavezsrbije.binding

import androidx.databinding.BindingAdapter
import com.overswayit.plesnisavezsrbije.viewmodels.CoupleRatingListViewModel
import com.overswayit.plesnisavezsrbije.views.CoupleRatingListView

/**
 * Created by lazarristic on 2019-06-13.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
class CoupleRatingListViewBindings {

    @BindingAdapter("viewModelRatingList")
    fun setViewModel(view: CoupleRatingListView, viewModel: CoupleRatingListViewModel) {
        view.setViewModel(viewModel)
    }
}