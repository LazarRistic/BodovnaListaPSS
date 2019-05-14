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
import kotlinx.android.synthetic.main.adjudicators_fragment.view.*

import java.util.ArrayList

class LaStAdjudicatorsFragment : Fragment() {

    private var mAdjudicatorAdapter: AdjudicatorAdapter? = null
    private val mAdjudicatorList = ArrayList<Adjudicator>()
    private var recyclerView: RecyclerView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
//        val view = inflater.inflate(R.layout.adjudicators_fragment, container, false)
//        recyclerView = view.findViewById(R.id.adjudicatorRecyclerView)
//
//        return view

        var binding: AdjudicatorsFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.adjudicators_fragment, container, false)
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
            val mViewModel = ViewModelProviders.of(this,
                    AdjudicatorsViewModelFactory(this.activity!!.application, AdjudicatorLicensesType.LA_ST)).get(AdjudicatorsViewModel::class.java)

            mViewModel.allAdjudicators.observe(this, Observer { adjudicators ->
                mAdjudicatorList.clear()
                mAdjudicatorList.addAll(adjudicators)
                mAdjudicatorAdapter!!.notifyDataSetChanged()
            })
        }

    }

    companion object {

        fun newInstance(): LaStAdjudicatorsFragment {
            return LaStAdjudicatorsFragment()
        }
    }
}