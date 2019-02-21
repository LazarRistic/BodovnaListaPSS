package com.overswayit.plesnisavezsrbije.activities;

import android.os.Bundle;

import com.overswayit.plesnisavezsrbije.R;
import com.overswayit.plesnisavezsrbije.models.Adjudicator;
import com.overswayit.plesnisavezsrbije.viewmodels.AdjudicatorsViewModel;
import com.overswayit.plesnisavezsrbije.views.AdjudicatorAdapter;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class AdjudicatorsActivity extends BaseActivity {

    @BindView(R.id.adjudicator_recycler_view)
    RecyclerView recyclerView;

    private AdjudicatorAdapter adjudicatorAdapter;
    private ArrayList<Adjudicator> adjudicatorList = new ArrayList<>();

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

        adjudicatorAdapter = new AdjudicatorAdapter(adjudicatorList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adjudicatorAdapter);

        AdjudicatorsViewModel viewModel = ViewModelProviders.of(this).get(AdjudicatorsViewModel.class);
        viewModel.getAllAdjudicators().observe(this, adjudicators -> {
            adjudicatorList.clear();
            adjudicatorList.addAll(adjudicators);
            adjudicatorAdapter.notifyDataSetChanged();
        });
    }
}
