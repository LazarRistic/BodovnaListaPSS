package com.overswayit.plesnisavezsrbije.fragments

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

/**
 * Created by lazarristic on 2019-05-09.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
class AdjudicatorsAdapter(fm: FragmentManager, private val numberOfTabs: Int) : FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {

        return when (position) {
            0 -> LaStAdjudicatorsFragment()
            1 -> ModernAdjudicatorsFragment()
            else -> ModernAdjudicatorsFragment()
        }
    }

    override fun getCount(): Int {
        return numberOfTabs
    }
}
