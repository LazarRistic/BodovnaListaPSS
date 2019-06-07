package com.overswayit.plesnisavezsrbije.fragments

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.overswayit.plesnisavezsrbije.MyApp
import com.overswayit.plesnisavezsrbije.R

/**
 * Created by lazarristic on 2019-06-06.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
class PointListAdapter(fm: FragmentManager, private val numberOfTabs: Int) : FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {

        return when (position) {
            0 -> PointListFragment.newInstance(MyApp.applicationContext().getString(R.string.la))
            1 -> PointListFragment.newInstance(MyApp.applicationContext().getString(R.string.st))
            else -> PointListFragment()
        }
    }

    override fun getCount(): Int {
        return numberOfTabs
    }
}