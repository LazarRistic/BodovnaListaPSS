package com.overswayit.plesnisavezsrbije.views

import android.content.Context
import android.content.res.TypedArray
import android.text.TextUtils
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import com.overswayit.plesnisavezsrbije.MyApp
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

    override fun bindingInflateView(inflater: LayoutInflater) {
        binding = DataBindingUtil.inflate(inflater, layoutId, this, false)

        addView(binding.root)
    }

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    override fun getParams(ta: TypedArray) {
        val pointsLatin = ta.getInt(R.styleable.CoupleRatingListView_pointsLatin, 0)
        val pointsStandard = ta.getInt(R.styleable.CoupleRatingListView_pointsStandard, 0)
        val pointsCombined = ta.getInt(R.styleable.CoupleRatingListView_pointsCombined, 0)
        val placeLatin = ta.getInt(R.styleable.CoupleRatingListView_placeLatin, 0)
        val placeStandard = ta.getInt(R.styleable.CoupleRatingListView_placeStandard, 0)
        val placeCombined = ta.getInt(R.styleable.CoupleRatingListView_placeCombined, 0)

        val points = Triple(pointsLatin, pointsStandard, pointsCombined)
        val place = Triple(placeLatin, placeStandard, placeCombined)

        setView(points, place)
    }

    fun setViewModel(viewModel: CoupleRatingListViewModel) {
        lifecycleScope.launchWhenResumed {
            val points = Triple(viewModel.pointsLatin, viewModel.pointsStandard, viewModel.pointsCombined)
            val place = Triple(viewModel.placeLatin, viewModel.placeStandard, viewModel.placeCombined)

            setView(points, place)
        }
    }

    private fun setView(points: Triple<Int?, Int?, Int?>, place: Triple<Int?, Int?, Int?>) {
        if (points.first != null) {
            binding.placeLatin.text = points.first.toString()
        }

        if (points.second != null) {
            binding.placeStandard.text = points.second.toString()
        }

        if (points.third != null) {
            binding.placeCombined.text = points.third.toString()
        }

        if (points.first != null) {
            binding.pointsLatin.text = place.first.toString()
        }

        if (points.second != null) {
            binding.pointsStandard.text = place.second.toString()
        }

        if (points.third != null) {
            binding.pointsCombined.text = place.third.toString()
        }
    }
}