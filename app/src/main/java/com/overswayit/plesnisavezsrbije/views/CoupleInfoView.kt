package com.overswayit.plesnisavezsrbije.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.overswayit.plesnisavezsrbije.R
import com.overswayit.plesnisavezsrbije.databinding.ViewCoupleInfoBinding
import com.overswayit.plesnisavezsrbije.utils.DisplayUtil
import com.overswayit.plesnisavezsrbije.viewmodels.CoupleInfoViewModel
import com.squareup.picasso.Picasso

/**
 * Created by lazarristic on 2019-06-14.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
class CoupleInfoView : BaseCompoundView {

    override val layoutId: Int
        get() = R.layout.view_couple_info

    private lateinit var binding: ViewCoupleInfoBinding

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    override fun bindingInflateView(inflater: LayoutInflater) {
        binding = DataBindingUtil.inflate(inflater, layoutId, this, false)

        addView(binding.root)
    }

    fun setViewModel(viewModel: CoupleInfoViewModel) {
        lifecycleScope.launchWhenResumed {
            binding.clubName.text = viewModel.clubName
            binding.clubTown.text = viewModel.clubTown
            binding.maleName.text = viewModel.nameMale
            binding.femaleName.text = viewModel.nameFemale
            binding.latinCategory.text = viewModel.latinCategoryText
            binding.standardCategory.text = viewModel.standardCategoryText
            binding.ageCategory.text = viewModel.ageCategoryText
            binding.latinCategory.backgroundTintList = androidx.databinding.adapters.Converters.convertColorToColorStateList(viewModel.latinCategoryColor)
            binding.standardCategory.backgroundTintList = androidx.databinding.adapters.Converters.convertColorToColorStateList(viewModel.standardCategoryColor)
            binding.ageCategory.backgroundTintList = androidx.databinding.adapters.Converters.convertColorToColorStateList(viewModel.ageCategoryColor)
            binding.followImage.setImageDrawable(context.resources.getDrawable(viewModel.followImage, null))
            binding.followImage.setOnClickListener {
                binding.followImage.setImageDrawable(context.resources.getDrawable(viewModel.toggleFavorite(), null))
            }

            Picasso.get()
                    .load(viewModel.coupleImageUrl)
                    .fit()
                    .into(binding.coupleImage)

            Picasso.get()
                    .load(viewModel.clubLogoUrl)
                    .resize(DisplayUtil.dpToPx(50), DisplayUtil.dpToPx(50))
                    .centerCrop()
                    .into(binding.clubImage)
        }
    }

}