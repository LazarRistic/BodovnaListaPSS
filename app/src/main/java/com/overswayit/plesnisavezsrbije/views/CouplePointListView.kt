package com.overswayit.plesnisavezsrbije.views

import android.content.Context
import android.content.res.TypedArray
import android.text.TextUtils
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.room.util.StringUtil
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
        val pointsLatin = ta.getInt(R.styleable.CouplePointListView_pointsLatin, 0)
        val pointsStandard = ta.getInt(R.styleable.CouplePointListView_pointsStandard, 0)
        val placeLatin = ta.getInt(R.styleable.CouplePointListView_placeLatin, 0)
        val placeStandard = ta.getInt(R.styleable.CouplePointListView_placeStandard, 0)
        val danceCategoryLatin = ta.getInt(R.styleable.CouplePointListView_danceCategoryLatin, 0)
        val danceCategoryStandard = ta.getInt(R.styleable.CouplePointListView_danceCategoryStandard, 0)

        val danceCategoryColorLatin = ta.getColor(R.styleable.CouplePointListView_danceCategoryColorLatin, context.getColor(R.color.white))
        val danceCategoryColorStandard = ta.getColor(R.styleable.CouplePointListView_danceCategoryColorStandard, context.getColor(R.color.white))

        setView(pointsLatin, pointsStandard, placeLatin, placeStandard, danceCategoryLatin.toString(), danceCategoryStandard.toString(), danceCategoryColorLatin, danceCategoryColorStandard)
    }

    fun setViewModel(viewModel: CouplePointListViewModel) {
        lifecycleScope.launchWhenResumed {
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
    }

    private fun setView(pointsLatin: Int?, pointsStandard: Int?, placeLatin: Int?, placeStandard: Int?, danceCategoryLatin: String?, danceCategoryStandard: String?, danceCategoryColorLatin: Int, danceCategoryColorStandard: Int) {
        if (pointsLatin != null) {
            binding.placeLatin.text = placeLatin.toString()
        }

        if (placeStandard != null) {
            binding.placeStandard.text = placeStandard.toString()
        }

        if (pointsLatin != null) {
            binding.pointsLatin.text = pointsLatin.toString()
        }

        if (pointsStandard != null) {
            binding.pointsStandard.text = pointsStandard.toString()
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