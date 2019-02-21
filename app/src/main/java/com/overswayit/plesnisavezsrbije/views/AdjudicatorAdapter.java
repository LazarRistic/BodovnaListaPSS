package com.overswayit.plesnisavezsrbije.views;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.overswayit.plesnisavezsrbije.R;
import com.overswayit.plesnisavezsrbije.models.Adjudicator;
import com.overswayit.plesnisavezsrbije.viewmodels.AdjudicatorViewModel;
import com.squareup.picasso.Picasso;

import java.util.List;

import agency.tango.android.avatarview.AvatarPlaceholder;
import agency.tango.android.avatarview.views.AvatarView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lazarristic on 21/02/2019.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
public class AdjudicatorAdapter extends RecyclerView.Adapter<AdjudicatorAdapter.AdjudicatorViewHolder> {

    private List<Adjudicator> adjudicators;

    public AdjudicatorAdapter(List<Adjudicator> adjudicators) {
        this.adjudicators = adjudicators;
    }

    @NonNull
    @Override
    public AdjudicatorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_adjudicator, parent, false);
        return new AdjudicatorAdapter.AdjudicatorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdjudicatorViewHolder holder, int position) {
        Adjudicator adjudicator = adjudicators.get(position);

        AdjudicatorViewModel viewModel = new AdjudicatorViewModel(adjudicator);
        holder.name.setText(viewModel.getName());
        holder.licenses.setText(viewModel.getLicenses());

        Picasso.get()
                .load(viewModel.getAvatarUri())
                .placeholder(new AvatarPlaceholder(viewModel.getName(), AvatarPlaceholder.DEFAULT_PLACEHOLDER_STRING))
                .fit()
                .into(holder.avatarView);
    }

    @Override
    public int getItemCount() {
        return adjudicators.size();
    }

    class AdjudicatorViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.adjudicator_avatar_view)
        AvatarView avatarView;
        @BindView(R.id.adjudicator_name)
        TextView name;
        @BindView(R.id.adjudicator_licenses)
        TextView licenses;

        AdjudicatorViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
