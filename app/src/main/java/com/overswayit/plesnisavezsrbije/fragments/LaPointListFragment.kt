package com.overswayit.plesnisavezsrbije.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.overswayit.plesnisavezsrbije.R
import com.overswayit.plesnisavezsrbije.activities.CoupleActivity
import com.overswayit.plesnisavezsrbije.activities.PointListActivity
import com.overswayit.plesnisavezsrbije.databinding.PointListFragmentBinding
import com.overswayit.plesnisavezsrbije.models.PointListItem
import com.overswayit.plesnisavezsrbije.viewmodels.LaListViewModel
import com.overswayit.plesnisavezsrbije.viewmodels.ListItemViewModel
import com.overswayit.plesnisavezsrbije.views.PointListItemAdapter
import java.util.*

/**
 * Created by lazarristic on 2019-07-31.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
class LaPointListFragment : Fragment() {
    private var pointListAdapter: PointListItemAdapter? = null
    private val pointListItemViewModels = ArrayList<ListItemViewModel>()
    private var recyclerView: RecyclerView? = null
    private lateinit var viewModel: LaListViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding: PointListFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.point_list_fragment, container, false)
        recyclerView = binding.pointListRecyclerView
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        pointListAdapter = PointListItemAdapter(pointListItemViewModels)
        pointListAdapter!!.setViewInteractionListener(object : PointListItemAdapter.ViewInteractionListener {
            override fun openCoupleActivity(pointListItem: PointListItem) {
                val intent = Intent(context, CoupleActivity::class.java)
                intent.putExtra(CoupleActivity.COUPLE_ID_KEY, pointListItem.couple.id)
                startActivity(intent)
            }
        })

        val layoutManager = LinearLayoutManager(context)
        recyclerView!!.layoutManager = layoutManager
        recyclerView!!.itemAnimator = DefaultItemAnimator()
        recyclerView!!.adapter = pointListAdapter

        if (activity != null) {
            viewModel = ViewModelProviders.of(this).get(LaListViewModel::class.java)

            (activity as PointListActivity).searchQuery().observe(this, Observer {
                viewModel.setSearchQuery(it)
            })

            observerPointList(viewModel.listCouples)
        }
    }

    private fun observerPointList(pointListCouples: LiveData<List<PointListItem>>) {
        pointListCouples.observe(this, Observer {
            changeDataSet(it)
        })
    }

    private fun changeDataSet(pointListItem: List<PointListItem>?) {
        pointListItemViewModels.clear()

        pointListItem?.forEach {
            pointListItemViewModels.add(ListItemViewModel(it))
        }

        pointListAdapter!!.notifyDataSetChanged()
        recyclerView!!.adapter!!.notifyDataSetChanged()
    }
}