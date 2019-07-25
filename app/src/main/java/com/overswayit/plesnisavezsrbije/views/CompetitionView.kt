package com.overswayit.plesnisavezsrbije.views

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.overswayit.plesnisavezsrbije.R
import com.overswayit.plesnisavezsrbije.databinding.ViewCompetitionLeagueItemBinding
import com.overswayit.plesnisavezsrbije.events.ViewMesssages
import com.overswayit.plesnisavezsrbije.utils.StringUtil
import com.overswayit.plesnisavezsrbije.viewmodels.CompetitionViewModel


/**
 * Created by lazarristic on 2019-07-24.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
class CompetitionView : BaseCompoundView {

    override val layoutId: Int
        get() = R.layout.view_competition_league_item

    private lateinit var binding: ViewCompetitionLeagueItemBinding

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    override fun bindingInflateView(inflater: LayoutInflater) {
        binding = DataBindingUtil.inflate(inflater, layoutId, this, false)
        addView(binding.root)
    }

    fun setViewModel(viewModel: CompetitionViewModel) {
        lifecycleScope.launchWhenResumed {
            binding.content.text = viewModel.categories
            binding.league.text = viewModel.title.asUIString()

            binding.pdfOne.visibility = viewModel.doc1Visibility()
            binding.pdfTwo.visibility = viewModel.doc2Visibility()

            binding.pdfOne.setOnClickListener {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(viewModel.doc1Url))
                it.context.startActivity(browserIntent)
            }

            binding.pdfTwo.setOnClickListener {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(viewModel.doc2Url))
                it.context.startActivity(browserIntent)
            }
        }
    }
}