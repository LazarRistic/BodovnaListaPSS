package com.overswayit.plesnisavezsrbije.fragments

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

/**
 * Created by lazarristic on 2019-07-25.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
class CompetitionViewPageAdapter(fm: FragmentManager, private val numberOfTabs: Int) : FragmentStatePagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {

        return when (position) {
            0 -> LaStCompetitionsFragment()
            1 -> ModernCompetitionsFragment()
            else -> LaStCompetitionsFragment()
        }
    }

    override fun getCount(): Int {
        return numberOfTabs
    }
}