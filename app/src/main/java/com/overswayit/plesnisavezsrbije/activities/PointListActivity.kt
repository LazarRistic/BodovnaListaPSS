package com.overswayit.plesnisavezsrbije.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.google.android.material.tabs.TabLayout
import com.overswayit.plesnisavezsrbije.R
import com.overswayit.plesnisavezsrbije.databinding.ActivityPointListBinding
import com.overswayit.plesnisavezsrbije.fragments.PointListAdapter
import kotlinx.android.synthetic.main.activity_point_list.*

class PointListActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityPointListBinding = DataBindingUtil.setContentView(this, R.layout.activity_point_list)

        val toolbar = binding.toolbar
        setSupportActionBar(toolbar)
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back)
        toolbar.setNavigationOnClickListener { onBackPressed() }
        toolbar.setTitle(R.string.point_list)

        binding.tabLayout.addTab(tabLayout!!.newTab().setText(getString(R.string.latin)))
        tabLayout.addTab(tabLayout!!.newTab().setText(getString(R.string.standard)))
        tabLayout.tabGravity = TabLayout.GRAVITY_FILL
        tabLayout.setTabTextColors(resources.getColor(R.color.white90, null), resources.getColor(R.color.white, null))

        val adapter = PointListAdapter(supportFragmentManager, tabLayout!!.tabCount)
        binding.viewPager.adapter = adapter
        binding.viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
        tabLayout!!.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab != null) {
                    binding.viewPager.currentItem = tab.position
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
