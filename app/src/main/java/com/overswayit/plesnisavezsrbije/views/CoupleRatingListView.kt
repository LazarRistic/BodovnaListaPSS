package com.overswayit.plesnisavezsrbije.views

import android.content.Context
import android.content.res.TypedArray
import android.text.TextUtils
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.databinding.DataBindingUtil
import com.overswayit.plesnisavezsrbije.R
import com.overswayit.plesnisavezsrbije.viewmodels.CoupleRatingListViewModel

/**
 * Created by lazarristic on 2019-06-13.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
class CoupleRatingListView : BaseCompoundView {

    override val layoutId: Int
        get() = R.layout.view_couple_rating_list

    override val styleableId: IntArray?
        get() = R.styleable.CoupleRatingListView

    private lateinit var binding: com.overswayit.plesnisavezsrbije.databinding.ViewCoupleRatingListBinding

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    override fun bindingInflateView(inflater: LayoutInflater) {
        binding = DataBindingUtil.inflate(inflater, layoutId, this, false)

        addView(binding.root)
    }

    override fun getParams(ta: TypedArray) {
        val pointsLatin = ta.getString(R.styleable.CoupleRatingListView_pointsLatin)
        val pointsStandard = ta.getString(R.styleable.CoupleRatingListView_pointsStandard)
        val pointsCombined = ta.getString(R.styleable.CoupleRatingListView_pointsCombined)
        val placeLatin = ta.getString(R.styleable.CoupleRatingListView_placeLatin)
        val placeStandard = ta.getString(R.styleable.CoupleRatingListView_placeStandard)
        val placeCombined = ta.getString(R.styleable.CoupleRatingListView_placeCombined)

        val points = Triple(pointsLatin, pointsStandard, pointsCombined)
        val place = Triple(placeLatin, placeStandard, placeCombined)

        setView(points, place)

    }

    fun setViewModel(viewModel: CoupleRatingListViewModel) {
        val points = Triple(viewModel.pointsLatin, viewModel.pointsStandard, viewModel.pointsCombined)
        val place = Triple(viewModel.placeLatin, viewModel.placeStandard, viewModel.placeCombined)

        setView(points, place)
    }

    private fun setView(points: Triple<String?, String?, String?>, place: Triple<String?, String?, String?>) {
        if (!TextUtils.isEmpty(points.first)) {
            binding.placeLatin.text = points.first
        }

        if (!TextUtils.isEmpty(points.second)) {
            binding.placeStandard.text = points.second
        }

        if (!TextUtils.isEmpty(points.third)) {
            binding.placeCombined.text = points.third
        }

        if (!TextUtils.isEmpty(place.first)) {
            binding.pointsLatin.text = place.first
        }

        if (!TextUtils.isEmpty(place.second)) {
            binding.pointsStandard.text = place.second
        }

        if (!TextUtils.isEmpty(place.third)) {
            binding.pointsCombined.text = place.third
        }
    }
}