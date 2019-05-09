package com.overswayit.plesnisavezsrbije.fragments;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

/**
 * Created by lazarristic on 2019-05-09.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
public class AdjudicatorsAdapter extends FragmentStatePagerAdapter {

    private int numberOfTabs;

    public AdjudicatorsAdapter(FragmentManager fm, int numberOfTabs) {
        super(fm);
        this.numberOfTabs = numberOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return new LaStAdjudicatorsFragment();
            case 1:
                return new ModernAdjudicatorsFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numberOfTabs;
    }
}
