package com.overswayit.plesnisavezsrbije.fragments

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

/**
 * Created by lazarristic on 2019-06-06.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
class PointListAdapter(fm: FragmentManager, private val numberOfTabs: Int) : FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {

        return when (position) {
            0 -> LaPointListFragment()
            1 -> StPointListFragment()
            else -> PointListFragment()
        }
    }

    override fun getCount(): Int {
        return numberOfTabs
    }
}