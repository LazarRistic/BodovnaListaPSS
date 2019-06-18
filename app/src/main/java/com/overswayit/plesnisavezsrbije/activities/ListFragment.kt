package com.overswayit.plesnisavezsrbije.activities

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.overswayit.plesnisavezsrbije.MainActivity
import com.overswayit.plesnisavezsrbije.R
import com.overswayit.plesnisavezsrbije.views.ListAdapter

class ListFragment : BaseFragment() {

    private lateinit var listAdapter: ListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding: com.overswayit.plesnisavezsrbije.databinding.FragmentListBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_list, container, false)

        (activity as MainActivity).setSupportActionBar(binding.toolbar)
        binding.toolbar.setNavigationIcon(R.drawable.ic_arrow_back)
        binding.toolbar.setNavigationOnClickListener { activity?.onBackPressed() }
        binding.toolbar.setTitle(R.string.list)

        listAdapter = ListAdapter()
        listAdapter.setViewInteractionListener(object : ListAdapter.ViewInteractionListener {
            override fun openListActivity(pair: Pair<Int, Int>) {
                this@ListFragment.openListActivity(pair)
            }
        })

        val layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        binding.listRecyclerView.layoutManager = layoutManager
        binding.listRecyclerView.itemAnimator = DefaultItemAnimator()
        binding.listRecyclerView.adapter = listAdapter

        return binding.root
    }

    private fun openListActivity(pair: Pair<Int, Int>) {
        val intent = Intent(activity, getActivityFromTitle(pair.first))
        startActivity(intent)
    }

    private fun getActivityFromTitle(stringRes: Int): Class<*>? {
        return when (stringRes) {
            R.string.point_list -> PointListActivity::class.java
            R.string.rating_list -> RatingListActivity::class.java
            R.string.favorite_couples -> PointListActivity::class.java
            else -> PointListActivity::class.java
        }
    }
}
