package com.overswayit.plesnisavezsrbije.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.overswayit.plesnisavezsrbije.R;
import com.overswayit.plesnisavezsrbije.models.Club;
import com.overswayit.plesnisavezsrbije.models.ClubContact;
import com.overswayit.plesnisavezsrbije.utils.ClubUtil;
import com.overswayit.plesnisavezsrbije.utils.StringUtil;
import com.overswayit.plesnisavezsrbije.viewmodels.ClubViewModel;
import com.overswayit.plesnisavezsrbije.viewmodels.ClubViewModelFactory;
import com.overswayit.plesnisavezsrbije.viewmodels.ClubsViewModel;
import com.overswayit.plesnisavezsrbije.views.ClubsAdapter;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ClubActivity extends AppCompatActivity {

    public static final int CALL_REQUEST = 1;
    public static final String CLUB_ID_KEY = "club id key";

    private Club club;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.club_logo)
    ImageView clubLogoImageView;

    @BindView(R.id.club_name)
    TextView clubNameTextView;

    @BindView(R.id.club_contact)
    TextView clubContactTextView;

    @BindView(R.id.contact_recycler_view)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_club);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());
        toolbar.setTitle(R.string.clubs);

        String[] PERMISSIONS = {android.Manifest.permission.CALL_PHONE};
        if (!hasPermissions(this, PERMISSIONS)) {
            ActivityCompat.requestPermissions(this, PERMISSIONS, 1);
        }

        ClubViewModel viewModel = ViewModelProviders.of(this, new ClubViewModelFactory(this.getApplication(), getClubId())).get(ClubViewModel.class);
        viewModel.getClub().observe(this, new Observer<Club>() {
            @Override
            public void onChanged(Club club) {
                clubNameTextView.setText(ClubUtil.getClubNameAndTown(club));
                clubContactTextView.setText(club.contactName);

                Picasso.get()
                        .load(club.logoUrl)
                        .error(R.drawable.ic_photo)
                        .into(clubLogoImageView, new Callback() {
                            @Override
                            public void onSuccess() {
                                clubLogoImageView.setVisibility(View.VISIBLE);
                            }

                            @Override
                            public void onError(Exception e) {

                            }
                        });

                ClubContactAdapter contactAdapter = new ClubContactAdapter(ClubUtil.getClubContacts(club));

//                clubsAdapter.setViewInteractionListener(ClubsActivity.this::openClubActivity);

                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(contactAdapter);
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case CALL_REQUEST: {
                if (grantResults.length <= 0 || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    new AlertDialog.Builder(ClubActivity.this)
                            .setMessage(R.string.need_call_permission)
                            .setPositiveButton(StringUtil.getString(R.string.ok), null)
                            .create()
                            .show();
                }
            }
        }
    }

    private static boolean hasPermissions(Context context, String... permissions) {
        if (context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }

        return true;
    }

    private int getClubId() {
        return getIntent().getIntExtra(CLUB_ID_KEY, 0);
    }
}
