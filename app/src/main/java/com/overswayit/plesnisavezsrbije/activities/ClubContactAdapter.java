package com.overswayit.plesnisavezsrbije.activities;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.overswayit.plesnisavezsrbije.App;
import com.overswayit.plesnisavezsrbije.R;
import com.overswayit.plesnisavezsrbije.models.ClubContact;
import com.overswayit.plesnisavezsrbije.utils.StringUtil;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by lazarristic on 18/04/2019.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
public class ClubContactAdapter extends RecyclerView.Adapter<ClubContactAdapter.ClubContactViewModel> {

    private ArrayList<ClubContact> clubContacts;

    public ClubContactAdapter(ArrayList<ClubContact> clubContacts) {
        this.clubContacts = clubContacts;
    }

    @NonNull
    @Override
    public ClubContactViewModel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_club_contact_item, parent, false);
        return new ClubContactAdapter.ClubContactViewModel(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClubContactViewModel holder, int position) {
        holder.setClubContact(clubContacts.get(position));
    }

    @Override
    public int getItemCount() {
        return clubContacts.size();
    }

    class ClubContactViewModel extends RecyclerView.ViewHolder {

        @BindView(R.id.text_view)
        TextView textView;
        @BindView(R.id.first_image_view)
        ImageView icon1;
        @BindView(R.id.second_image_view)
        ImageView icon2;

        private ClubContact clubContact;

        ClubContactViewModel(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setClubContact(ClubContact clubContact) {
            this.clubContact = clubContact;
            this.textView.setText(clubContact.contact);
            this.icon2.setVisibility(View.GONE);

            switch (clubContact.type) {
                case EMAIL:
                case ADDRESS:
                case LAND_LINE:
                    if (clubContact.contacts != null && !clubContact.contacts.isEmpty()) {
                        icon1.setImageDrawable(App.getContext().getDrawable(clubContact.contacts.get(0).second));
                        icon1.setVisibility(View.VISIBLE);
                    }
                    break;
                case MOBILE:
                    if (clubContact.contacts != null && !clubContact.contacts.isEmpty()) {
                        icon2.setImageDrawable(App.getContext().getDrawable(clubContact.contacts.get(0).second));
                        icon1.setImageDrawable(App.getContext().getDrawable(clubContact.contacts.get(1).second));
                        icon2.setVisibility(View.VISIBLE);
                    }
                    break;
            }
        }

        @OnClick({R.id.first_image_view})
        public void onFirstItemClicked(View view) {
            switch (clubContact.type) {
                case EMAIL:
                    onEmailClicked(view);
                    break;
                case ADDRESS:
                    onAddressClicked(view);
                    break;
                case LAND_LINE:
                case MOBILE:
                    onCallClicked(view);
                    break;
            }
        }

        @OnClick({R.id.second_image_view})
        public void onSecondItemClicked(View view) {
            switch (clubContact.type) {
                case MOBILE:
                    onMobileMessageClicked(view);
                    break;
                case EMAIL:
                case ADDRESS:
                case LAND_LINE:
                    break;
            }
        }

        private void onEmailClicked(View view) {
            final Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);

            emailIntent.setType("plain/text");
            emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{clubContact.contact});
            view.getContext().startActivity(Intent.createChooser(emailIntent, StringUtil.getString(R.string.send_email)));
        }

        private void onAddressClicked(View view) {
            Uri gmmIntentUri = Uri.parse("geo:0,0?q=" + clubContact.contact);
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
            mapIntent.setPackage("com.google.android.apps.maps");
            view.getContext().startActivity(mapIntent);
        }

        private void onCallClicked(View view) {
            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "Your Phone_number"));
            if (ActivityCompat.checkSelfPermission(view.getContext(), Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                view.getContext().startActivity(intent);
            } else {
                ActivityCompat.requestPermissions((Activity) view.getContext(), new String[]{Manifest.permission.CALL_PHONE}, ClubActivity.CALL_REQUEST);
            }
        }

        private void onMobileMessageClicked(View view) {
            Intent sendIntent = new Intent(Intent.ACTION_VIEW);
            sendIntent.setData(Uri.parse("sms:" + clubContact.contact));
            view.getContext().startActivity(sendIntent);
        }
    }
}
