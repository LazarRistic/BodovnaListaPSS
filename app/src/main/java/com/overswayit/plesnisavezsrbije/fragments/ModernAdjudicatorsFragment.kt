package com.overswayit.plesnisavezsrbije.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.overswayit.plesnisavezsrbije.R
import com.overswayit.plesnisavezsrbije.databinding.AdjudicatorsFragmentBinding
import com.overswayit.plesnisavezsrbije.models.Adjudicator
import com.overswayit.plesnisavezsrbije.models.AdjudicatorLicensesType
import com.overswayit.plesnisavezsrbije.viewmodels.AdjudicatorsViewModel
import com.overswayit.plesnisavezsrbije.viewmodels.AdjudicatorsViewModelFactory
import com.overswayit.plesnisavezsrbije.views.AdjudicatorAdapter

import java.util.ArrayList

/**
 * Created by lazarristic on 2019-05-09.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
class ModernAdjudicatorsFragment : Fragment() {
    private var recyclerView: RecyclerView? = null

    private var mAdjudicatorAdapter: AdjudicatorAdapter? = null
    private val mAdjudicatorList = ArrayList<Adjudicator>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding: AdjudicatorsFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.adjudicators_fragment, container, false)
        recyclerView = binding.adjudicatorRecyclerView

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mAdjudicatorAdapter = AdjudicatorAdapter(mAdjudicatorList)
        val layoutManager = LinearLayoutManager(context)
        recyclerView!!.layoutManager = layoutManager
        recyclerView!!.itemAnimator = DefaultItemAnimator()
        recyclerView!!.adapter = mAdjudicatorAdapter


        if (activity != null) {
            val viewModel = ViewModelProviders.of(this,
                    AdjudicatorsViewModelFactory(AdjudicatorLicensesType.MODERN)).get(AdjudicatorsViewModel::class.java)
            viewModel.allAdjudicators.observe(this, Observer { adjudicators ->
                mAdjudicatorList.clear()
                mAdjudicatorList.addAll(adjudicators)
                mAdjudicatorAdapter!!.notifyDataSetChanged()
            })
        }

    }
}
