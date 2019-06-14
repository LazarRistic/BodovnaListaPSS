package com.overswayit.plesnisavezsrbije.views

import android.content.Context
import android.content.res.TypedArray
import android.text.TextUtils
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.databinding.DataBindingUtil
import com.overswayit.plesnisavezsrbije.R
import com.overswayit.plesnisavezsrbije.databinding.ViewCouplePointListBinding
import com.overswayit.plesnisavezsrbije.viewmodels.CouplePointListViewModel


/**
 * Created by lazarristic on 2019-06-10.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
class CouplePointListView : BaseCompoundView {

    override val layoutId: Int
        get() = R.layout.view_couple_point_list

    override val styleableId: IntArray?
        get() = R.styleable.CouplePointListView

    private lateinit var binding: ViewCouplePointListBinding

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    override fun bindingInflateView(inflater: LayoutInflater) {
        binding = DataBindingUtil.inflate(inflater, layoutId, this, false)

        addView(binding.root)
    }

    override fun getParams(ta: TypedArray) {
        val pointsLatin = ta.getString(R.styleable.CouplePointListView_pointsLatin)
        val pointsStandard = ta.getString(R.styleable.CouplePointListView_pointsStandard)
        val placeLatin = ta.getString(R.styleable.CouplePointListView_placeLatin)
        val placeStandard = ta.getString(R.styleable.CouplePointListView_placeStandard)
        val danceCategoryLatin = ta.getString(R.styleable.CouplePointListView_danceCategoryLatin)
        val danceCategoryStandard = ta.getString(R.styleable.CouplePointListView_danceCategoryStandard)

        val danceCategoryColorLatin = ta.getColor(R.styleable.CouplePointListView_danceCategoryColorLatin, context.getColor(R.color.white))
        val danceCategoryColorStandard = ta.getColor(R.styleable.CouplePointListView_danceCategoryColorStandard, context.getColor(R.color.white))

        setView(pointsLatin, pointsStandard, placeLatin, placeStandard, danceCategoryLatin, danceCategoryStandard, danceCategoryColorLatin, danceCategoryColorStandard)
    }

    fun setViewModel(viewModel: CouplePointListViewModel) {
        val pointsLatin = viewModel.pointsLatin
        val pointsStandard = viewModel.pointsStandard
        val placeLatin = viewModel.placeLatin
        val placeStandard = viewModel.placeStandard
        val danceCategoryLatin = viewModel.danceCategoryLatin
        val danceCategoryStandard = viewModel.danceCategoryStandard

        val danceCategoryColorLatin = viewModel.danceCategoryColorLatin
        val danceCategoryColorStandard = viewModel.danceCategoryColorStandard

        setView(pointsLatin, pointsStandard, placeLatin, placeStandard, danceCategoryLatin, danceCategoryStandard, danceCategoryColorLatin, danceCategoryColorStandard)
    }

    private fun setView(pointsLatin: String?, pointsStandard: String?, placeLatin: String?, placeStandard: String?, danceCategoryLatin: String?, danceCategoryStandard: String?, danceCategoryColorLatin: Int, danceCategoryColorStandard: Int) {
        if (!TextUtils.isEmpty(pointsLatin)) {
            binding.placeLatin.text = placeLatin
        }

        if (!TextUtils.isEmpty(pointsStandard)) {
            binding.placeStandard.text = placeStandard
        }

        if (!TextUtils.isEmpty(placeLatin)) {
            binding.pointsLatin.text = pointsLatin

        }

        if (!TextUtils.isEmpty(placeStandard)) {
            binding.pointsStandard.text = pointsStandard
        }

        if (!TextUtils.isEmpty(danceCategoryLatin)) {
            binding.danceClassLatin.text = danceCategoryLatin
        }

        if (!TextUtils.isEmpty(danceCategoryStandard)) {
            binding.danceClassStandard.text = danceCategoryStandard
        }

        binding.danceClassLatin.backgroundTintList = androidx.databinding.adapters.Converters.convertColorToColorStateList(danceCategoryColorLatin)
        binding.danceClassStandard.backgroundTintList = androidx.databinding.adapters.Converters.convertColorToColorStateList(danceCategoryColorStandard)
    }
}