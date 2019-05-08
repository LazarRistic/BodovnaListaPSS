package com.overswayit.plesnisavezsrbije.views;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.overswayit.plesnisavezsrbije.App;
import com.overswayit.plesnisavezsrbije.R;
import com.overswayit.plesnisavezsrbije.activities.ClubActivity;
import com.overswayit.plesnisavezsrbije.models.Club;
import com.overswayit.plesnisavezsrbije.utils.ClubUtil;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by lazarristic on 19/02/2019.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
public class ClubsAdapter extends RecyclerView.Adapter<ClubsAdapter.ClubsViewHolder> {

    private List<Club> clubList;

    public ClubsAdapter(List<Club> clubList) {
        this.clubList = clubList;
    }

    public interface  ViewInteractionListener {
        void openClubActivity(Club club);
    }

    private ViewInteractionListener viewInteractionListener;

    @NonNull
    @Override
    public ClubsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_club, parent, false);
        return new ClubsAdapter.ClubsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClubsViewHolder holder, int position) {
        Club club = clubList.get(position);

        Picasso.get()
                .load(club.logoUrl)
                .error(R.drawable.ic_photo)
                .into(holder.logo, new Callback() {
                    @Override
                    public void onSuccess() {
                        holder.logo.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onError(Exception e) {

                    }
                });

        holder.name.setText(ClubUtil.getClubNameAndTown(club));
        holder.contact.setText(club.contactName);
        holder.setClub(club);

        holder.itemView.setOnClickListener(v -> viewInteractionListener.openClubActivity(club));
    }

    @Override
    public int getItemCount() {
        return clubList.size();
    }

    public void setViewInteractionListener(ViewInteractionListener viewInteractionListener) {
        this.viewInteractionListener = viewInteractionListener;
    }

    class ClubsViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.club_name)
        TextView name;
        @BindView(R.id.club_logo)
        ImageView logo;
        @BindView(R.id.club_contact)
        TextView contact;

        private Club club;

        ClubsViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void setClub(Club club) {
            this.club = club;
        }
    }
}
