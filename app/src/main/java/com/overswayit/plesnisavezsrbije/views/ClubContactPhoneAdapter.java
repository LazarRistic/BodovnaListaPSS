package com.overswayit.plesnisavezsrbije.views;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.overswayit.plesnisavezsrbije.R;
import com.overswayit.plesnisavezsrbije.events.ViewMesssages;
import com.overswayit.plesnisavezsrbije.viewmodels.ClubContactPhoneViewModel;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by lazarristic on 19/02/2019.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
public class ClubContactPhoneAdapter extends RecyclerView.Adapter<ClubContactPhoneAdapter.ClubContactPhoneViewHolder> {

    private ArrayList<String> phoneNumbers;

    public ClubContactPhoneAdapter(ArrayList<String> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    @NonNull
    @Override
    public ClubContactPhoneViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_club_contact_phone_item, parent, false);
        return new ClubContactPhoneAdapter.ClubContactPhoneViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClubContactPhoneViewHolder holder, int position) {
        String phone = phoneNumbers.get(position);
        holder.setupView(new ClubContactPhoneViewModel(phone));
    }

    @Override
    public int getItemCount() {
        return phoneNumbers.size();
    }

    class ClubContactPhoneViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.club_contact_phone)
        TextView phoneNumber;
        @BindView(R.id.club_contact_phone_call_button)
        ImageView callButton;
        @BindView(R.id.club_contact_phone_message_button)
        ImageView messageButton;

        private ClubContactPhoneViewModel phoneViewModel;

        ClubContactPhoneViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void setupView(ClubContactPhoneViewModel viewModel) {
            phoneViewModel = viewModel;

            phoneNumber.setText(viewModel.getPhoneNumber());
            callButton.setVisibility(viewModel.showCallButton());
            messageButton.setVisibility(viewModel.showMessageButton());
        }

        @OnClick({R.id.club_contact_phone_call_button})
        public void onCall(View view) {
            ViewMesssages.clubContactPhoneCall.post(phoneViewModel.getPhoneNumber());
        }

        @OnClick({R.id.club_contact_phone_message_button})
        public void onMessage(View view) {
            ViewMesssages.clubContactPhoneMessage.post(phoneViewModel.getPhoneNumber());
        }
    }
}
