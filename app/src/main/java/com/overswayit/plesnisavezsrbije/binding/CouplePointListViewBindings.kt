package com.overswayit.plesnisavezsrbije.binding

import androidx.databinding.BindingAdapter
import com.overswayit.plesnisavezsrbije.viewmodels.CouplePointListViewModel
import com.overswayit.plesnisavezsrbije.views.CouplePointListView

/**
 * Created by lazarristic on 2019-06-13.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
class CouplePointListViewBindings {

    @BindingAdapter("viewModelPointList")
    fun setViewModel(view: CouplePointListView, viewModel: CouplePointListViewModel) {
        view.setViewModel(viewModel)
    }

}