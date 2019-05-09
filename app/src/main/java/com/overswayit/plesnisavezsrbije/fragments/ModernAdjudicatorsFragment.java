package com.overswayit.plesnisavezsrbije.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.overswayit.plesnisavezsrbije.R;
import com.overswayit.plesnisavezsrbije.models.Adjudicator;
import com.overswayit.plesnisavezsrbije.models.AdjudicatorLicensesType;
import com.overswayit.plesnisavezsrbije.viewmodels.AdjudicatorsViewModel;
import com.overswayit.plesnisavezsrbije.viewmodels.AdjudicatorsViewModelFactory;
import com.overswayit.plesnisavezsrbije.views.AdjudicatorAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by lazarristic on 2019-05-09.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
public class ModernAdjudicatorsFragment extends Fragment {
    @BindView(R.id.adjudicator_recycler_view)
    RecyclerView recyclerView;
    private Unbinder unbinder;

    private AdjudicatorAdapter mAdjudicatorAdapter;
    private ArrayList<Adjudicator> mAdjudicatorList = new ArrayList<>();

    public static ModernAdjudicatorsFragment newInstance() {
        return new ModernAdjudicatorsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.adjudicators_fragment, container, false);
        unbinder = ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mAdjudicatorAdapter = new AdjudicatorAdapter(mAdjudicatorList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdjudicatorAdapter);


        if (getActivity() != null) {
            AdjudicatorsViewModel mViewModel = ViewModelProviders.of(this,
                    new AdjudicatorsViewModelFactory(this.getActivity().getApplication(), AdjudicatorLicensesType.MODERN)).get(AdjudicatorsViewModel.class);

            mViewModel.getAllAdjudicators().observe(this, adjudicators -> {
                mAdjudicatorList.clear();
                mAdjudicatorList.addAll(adjudicators);
                mAdjudicatorAdapter.notifyDataSetChanged();
            });
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
