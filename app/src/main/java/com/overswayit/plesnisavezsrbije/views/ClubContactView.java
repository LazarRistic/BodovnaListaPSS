package com.overswayit.plesnisavezsrbije.views;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.overswayit.plesnisavezsrbije.App;
import com.overswayit.plesnisavezsrbije.R;
import com.overswayit.plesnisavezsrbije.events.ViewMesssages;
import com.overswayit.plesnisavezsrbije.models.Club;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by lazarristic on 19/02/2019.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
public class ClubContactView extends BaseCompoundView {

    @BindView(R.id.club_contact_name)
    TextView name;
    @BindView(R.id.club_contact_email)
    TextView email;
    @BindView(R.id.club_contact_phone_recycle_view)
    RecyclerView recyclerView;
    @BindView(R.id.club_contact_email_holder)
    View emailHolder;

    private Club club;

    public ClubContactView(Context context) {
        super(context);
    }

    public ClubContactView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ClubContactView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.view_club_contact;
    }

    @Override
    protected void bindViews(View contentView) {
        ButterKnife.bind(this, contentView);
    }

    public void setupView(Club club) {
        this.club = club;

        ClubContactPhoneAdapter phoneAdapter = new ClubContactPhoneAdapter(club.phoneNumbers);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(App.getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(phoneAdapter);

        name.setText(club.contactName);
        email.setText(club.email);

        int visibility = TextUtils.isEmpty(club.email) ? GONE : VISIBLE;
        emailHolder.setVisibility(visibility);
    }

    @OnClick({R.id.club_contact_email_button})
    public void onEmail(View view) {
        ViewMesssages.clubContactEmail.post(club.email);
    }
}
