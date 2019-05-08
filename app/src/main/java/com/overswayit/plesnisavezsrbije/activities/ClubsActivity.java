package com.overswayit.plesnisavezsrbije.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import com.overswayit.plesnisavezsrbije.R;
import com.overswayit.plesnisavezsrbije.events.EventBus;
import com.overswayit.plesnisavezsrbije.events.ViewMesssages;
import com.overswayit.plesnisavezsrbije.models.Club;
import com.overswayit.plesnisavezsrbije.viewmodels.ClubsViewModel;
import com.overswayit.plesnisavezsrbije.views.ClubsAdapter;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;

import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ClubsActivity extends BaseActivity {

    private final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 0;

    @BindView(R.id.clubs_recycler_view)
    RecyclerView recyclerView;

    private ClubsAdapter clubsAdapter;
    private ArrayList<Club> clubList = new ArrayList<Club>();

    @Override
    protected void onResume() {
        super.onResume();
        EventBus.register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        EventBus.unregister(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clubs);
        ButterKnife.bind(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());
        toolbar.setTitle(R.string.clubs);

        clubsAdapter = new ClubsAdapter(clubList);
        clubsAdapter.setViewInteractionListener(ClubsActivity.this::openClubActivity);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(clubsAdapter);

        ClubsViewModel viewModel = ViewModelProviders.of(this).get(ClubsViewModel.class);
        viewModel.getAllClubs().observe(this, clubs -> {
            clubList.clear();
            clubList.addAll(clubs);
            clubsAdapter.notifyDataSetChanged();
        });

        if (checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(ClubsActivity.this,
                    new String[]{Manifest.permission.CALL_PHONE},
                    MY_PERMISSIONS_REQUEST_CALL_PHONE);
        }
    }

    @Subscribe
    public void on(ViewMesssages.ClubContactEmail message) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, message.content);
        intent.putExtra(Intent.EXTRA_SUBJECT, R.string.pps_app);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    @Subscribe
    public void on(ViewMesssages.ClubContactPhoneCall message) {
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:" + message.content));
        if (checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(ClubsActivity.this,
                    new String[]{Manifest.permission.CALL_PHONE},
                    MY_PERMISSIONS_REQUEST_CALL_PHONE);
            return;
        }

        startActivity(callIntent);
    }

    @Subscribe
    public void on(ViewMesssages.ClubContactPhoneMessage message) {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.fromParts("sms", message.content, null)));
    }

    private void openClubActivity(Club club) {
        Intent intent = new Intent(this, ClubActivity.class);
        intent.putExtra(ClubActivity.CLUB_ID_KEY, club.id);
        startActivity(intent);
    }
}
