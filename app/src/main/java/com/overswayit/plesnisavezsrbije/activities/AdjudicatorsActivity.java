package com.overswayit.plesnisavezsrbije.activities;

import android.content.res.ColorStateList;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.overswayit.plesnisavezsrbije.R;
import com.overswayit.plesnisavezsrbije.fragments.AdjudicatorsAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdjudicatorsActivity extends BaseActivity {

    @BindView(R.id.tab_layout)
    TabLayout tabLayout;

    @BindView(R.id.pager)
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adjudicators);
        ButterKnife.bind(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());
        toolbar.setTitle(R.string.adjudicators);

        tabLayout.addTab(tabLayout.newTab().setText("Latino i Standard"));
        tabLayout.addTab(tabLayout.newTab().setText("Savremeni"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setTabTextColors(getResources().getColor(R.color.white90), getResources().getColor(R.color.white));

        final AdjudicatorsAdapter adapter = new AdjudicatorsAdapter
                (getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
