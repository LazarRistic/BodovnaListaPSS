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
import com.overswayit.plesnisavezsrbije.databinding.RatingListFragmentBinding
import com.overswayit.plesnisavezsrbije.models.DanceType
import com.overswayit.plesnisavezsrbije.models.RatingListItem
import com.overswayit.plesnisavezsrbije.viewmodels.ListItemViewModel
import com.overswayit.plesnisavezsrbije.viewmodels.ListViewModel
import com.overswayit.plesnisavezsrbije.views.RatingListItemAdapter

/**
 * Created by lazarristic on 2019-06-07.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
private const val ARG_DANCE_TYPE = "arg_dance_type"

class RatingListFragment : Fragment() {

    private var danceType: DanceType? = null
    private var ratingListAdapter: RatingListItemAdapter? = null
    private val ratingListItemViewModels = ArrayList<ListItemViewModel>()
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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: RatingListFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.rating_list_fragment, container, false)
        recyclerView = binding.ratingListRecyclerView
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        ratingListAdapter = RatingListItemAdapter(ratingListItemViewModels)
        ratingListAdapter!!.setViewInteractionListener(object : RatingListItemAdapter.ViewInteractionListener {
            override fun openCoupleActivity(ratingListItem: RatingListItem) {
                val intent = Intent(context, CoupleActivity::class.java)
                intent.putExtra(CoupleActivity.COUPLE_ID_KEY, ratingListItem.couple.id)
                startActivity(intent)
            }
        })

        val layoutManager = LinearLayoutManager(context)
        recyclerView!!.layoutManager = layoutManager
        recyclerView!!.itemAnimator = DefaultItemAnimator()
        recyclerView!!.adapter = ratingListAdapter

        if (activity != null) {
            val viewModel = ViewModelProviders.of(this).get(ListViewModel::class.java)

            when (danceType) {
                DanceType.LA -> createLaViewModel(viewModel)
                DanceType.ST -> createStViewModel(viewModel)
                DanceType.KM -> createKmViewModel(viewModel)
            }
        }
    }

    private fun createLaViewModel(viewModel: ListViewModel) {
        viewModel.laRatingListCouples.observe(this, Observer { ratingListItem ->
            ratingListItemViewModels.clear()

            ratingListItem.forEach {
                ratingListItemViewModels.add(ListItemViewModel(it))
            }

            ratingListAdapter!!.notifyDataSetChanged()
            recyclerView!!.adapter!!.notifyDataSetChanged()
        })
    }

    private fun createStViewModel(viewModel: ListViewModel) {
        viewModel.stRatingListCouples.observe(this, Observer { ratingListItem ->
            ratingListItemViewModels.clear()

            ratingListItem.forEach {
                ratingListItemViewModels.add(ListItemViewModel(it))
            }

            ratingListAdapter!!.notifyDataSetChanged()
            recyclerView!!.adapter!!.notifyDataSetChanged()
        })
    }

    private fun createKmViewModel(viewModel: ListViewModel) {
        viewModel.kmRatingListCouples.observe(this, Observer { ratingListItem ->
            ratingListItemViewModels.clear()

            ratingListItem.forEach {
                ratingListItemViewModels.add(ListItemViewModel(it))
            }

            ratingListAdapter!!.notifyDataSetChanged()
            recyclerView!!.adapter!!.notifyDataSetChanged()
        })
    }

    companion object {
        @JvmStatic
        fun newInstance(danceType: String) =
                RatingListFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_DANCE_TYPE, danceType)
                    }
                }
    }
}