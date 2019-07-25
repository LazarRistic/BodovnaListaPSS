package com.overswayit.plesnisavezsrbije.activities


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.google.android.material.tabs.TabLayout
import com.overswayit.plesnisavezsrbije.MainActivity
import com.overswayit.plesnisavezsrbije.R
import com.overswayit.plesnisavezsrbije.databinding.FragmentCompetitionBinding
import com.overswayit.plesnisavezsrbije.fragments.CompetitionViewPageAdapter


class CompetitionFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding: FragmentCompetitionBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_competition, container, false)

        val toolbar = binding.toolbar
        (activity as MainActivity).setSupportActionBar(toolbar)
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back)
        toolbar.setNavigationOnClickListener { activity?.onBackPressed() }
        toolbar.setTitle(getString(R.string.competitions) + " - 2019")

        val tabLayout = binding.tabLayout
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.latin_and_standard)))
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.modern)))
        tabLayout.tabGravity = TabLayout.GRAVITY_FILL
        tabLayout.setTabTextColors(resources.getColor(R.color.white90, null), resources.getColor(R.color.white, null))

        val adapter = CompetitionViewPageAdapter(activity?.supportFragmentManager!!, tabLayout.tabCount)
        val viewPager = binding.viewPager
        viewPager.adapter = adapter
        viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab != null) {
                    viewPager.currentItem = tab.position
                }
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                //Ignored
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                //Ignored
            }
        })

        return binding.root
    }
}