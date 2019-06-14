package com.overswayit.plesnisavezsrbije.binding

import androidx.databinding.BindingAdapter
import com.overswayit.plesnisavezsrbije.viewmodels.CoupleInfoViewModel
import com.overswayit.plesnisavezsrbije.views.CoupleInfoView

/**
 * Created by lazarristic on 2019-06-14.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
class CoupleInfoViewBindings {

    @BindingAdapter("viewModel")
    fun setViewModel(view: CoupleInfoView, viewModel: CoupleInfoViewModel) {
        view.setViewModel(viewModel)
    }
}