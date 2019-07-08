package com.overswayit.plesnisavezsrbije.fragments

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
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
import com.overswayit.plesnisavezsrbije.activities.CoupleActivity
import com.overswayit.plesnisavezsrbije.databinding.PointListFragmentBinding
import com.overswayit.plesnisavezsrbije.models.DanceType
import com.overswayit.plesnisavezsrbije.models.PointListItem
import com.overswayit.plesnisavezsrbije.viewmodels.ListItemViewModel
import com.overswayit.plesnisavezsrbije.viewmodels.ListViewModel
import com.overswayit.plesnisavezsrbije.views.PointListItemAdapter
import java.util.*

/**
 * Created by lazarristic on 2019-06-06.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */

private const val ARG_DANCE_TYPE = "arg_dance_type"

class PointListFragment : Fragment() {

    private var danceType: DanceType? = null
    private var pointListAdapter: PointListItemAdapter? = null
    private val pointListItemViewModels = ArrayList<ListItemViewModel>()
    private var recyclerView: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            val danceTypeValue = it.getString(ARG_DANCE_TYPE)

            if (!TextUtils.isEmpty(danceTypeValue)) {
                danceType = DanceType.valueOf(danceTypeValue!!)
            }
        }
    }

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
            val viewModel = ViewModelProviders.of(this).get(ListViewModel::class.java)

            when (danceType) {
                DanceType.LA -> createLaViewModel(viewModel)
                DanceType.ST -> createStViewModel(viewModel)
                else -> {
                }
            }
        }
    }

    private fun createLaViewModel(viewModel: ListViewModel) {
        viewModel.laPointListCouples.observe(this, Observer { pointListItem ->
            pointListItemViewModels.clear()

            pointListItem.forEach {
                pointListItemViewModels.add(ListItemViewModel(it))
            }

            pointListAdapter!!.notifyDataSetChanged()
            recyclerView!!.adapter!!.notifyDataSetChanged()
        })
    }

    private fun createStViewModel(viewModel: ListViewModel) {
        viewModel.stPointListCouples.observe(this, Observer { pointListItem ->
            pointListItemViewModels.clear()

            pointListItem.forEach {
                pointListItemViewModels.add(ListItemViewModel(it))
            }

            pointListAdapter!!.notifyDataSetChanged()
            recyclerView!!.adapter!!.notifyDataSetChanged()
        })
    }

    companion object {
        @JvmStatic
        fun newInstance(danceType: String) =
                PointListFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_DANCE_TYPE, danceType)
                    }
                }
    }
}