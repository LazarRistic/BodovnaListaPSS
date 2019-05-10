package com.overswayit.plesnisavezsrbije.activities

import android.os.Bundle
import androidx.databinding.DataBindingUtil.setContentView
import com.google.android.material.tabs.TabLayout
import com.overswayit.plesnisavezsrbije.R
import com.overswayit.plesnisavezsrbije.databinding.ActivityAdjudicatorsBinding
import com.overswayit.plesnisavezsrbije.fragments.AdjudicatorsAdapter
import kotlinx.android.synthetic.main.activity_adjudicators.*

class AdjudicatorsActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_adjudicators)
        val binding: ActivityAdjudicatorsBinding = setContentView(this, R.layout.activity_adjudicators)

        setSupportActionBar(binding.toolbar)
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back)
        toolbar.setNavigationOnClickListener { onBackPressed() }
        toolbar.setTitle(R.string.adjudicators)

        binding.tabLayout.addTab(tabLayout!!.newTab().setText(getString(R.string.latin_and_standard)))
        tabLayout.addTab(tabLayout!!.newTab().setText(getString(R.string.modern)))
        tabLayout.tabGravity = TabLayout.GRAVITY_FILL
        tabLayout.setTabTextColors(resources.getColor(R.color.white90, null), resources.getColor(R.color.white, null))

        val adapter = AdjudicatorsAdapter(supportFragmentManager, tabLayout!!.tabCount)
        binding.viewPager.adapter = adapter
        viewPager!!.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
        tabLayout!!.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab != null) {
                    viewPager!!.currentItem = tab.position
                }
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                //Ignored
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                //Ignored
            }
        })
    }
}
