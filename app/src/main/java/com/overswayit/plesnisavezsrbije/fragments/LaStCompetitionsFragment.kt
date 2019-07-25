package com.overswayit.plesnisavezsrbije.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.overswayit.plesnisavezsrbije.R
import com.overswayit.plesnisavezsrbije.databinding.CompetitionFragmentBinding
import com.overswayit.plesnisavezsrbije.models.FederationDanceType
import com.overswayit.plesnisavezsrbije.viewmodels.*
import com.overswayit.plesnisavezsrbije.views.CompetitionsEventsAdapter
import java.util.*

/**
 * Created by lazarristic on 2019-07-25.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
class LaStCompetitionsFragment : Fragment() {

    private var competitionsAdapter: CompetitionsEventsAdapter? = null
    private val competitionList = ArrayList<CompetitionEventViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            val viewModel = ViewModelProviders.of(this,
                    CompetitionsEventsViewModelFactory(FederationDanceType.LA_ST, activity!!.application)).get(CompetitionsEventsViewModel::class.java)

            viewLifecycleOwner.lifecycleScope.launchWhenCreated {
                viewModel.competitions.observe(this@LaStCompetitionsFragment, Observer {
                    competitionList.clear()
                    competitionList.addAll(it)
                    competitionsAdapter!!.notifyDataSetChanged()
                })
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding: CompetitionFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.competition_fragment, container, false)

        competitionsAdapter = CompetitionsEventsAdapter(competitionList)

        val layoutManager = LinearLayoutManager(activity)
        binding.competitionsRecyclerView.layoutManager = layoutManager
        binding.competitionsRecyclerView.itemAnimator = DefaultItemAnimator()
        binding.competitionsRecyclerView.adapter = competitionsAdapter

        return binding.root
    }
}