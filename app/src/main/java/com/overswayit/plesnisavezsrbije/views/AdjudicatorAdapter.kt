package com.overswayit.plesnisavezsrbije.views

import agency.tango.android.avatarview.AvatarPlaceholder
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.overswayit.plesnisavezsrbije.databinding.ViewAdjudicatorBinding
import com.overswayit.plesnisavezsrbije.models.Adjudicator
import com.overswayit.plesnisavezsrbije.utils.DisplayUtil.dpToPx
import com.overswayit.plesnisavezsrbije.viewmodels.AdjudicatorViewModel
import com.squareup.picasso.Picasso

/**
 * Created by lazarristic on 21/02/2019.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
class AdjudicatorAdapter(private val adjudicators: List<Adjudicator>) : RecyclerView.Adapter<AdjudicatorAdapter.AdjudicatorViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdjudicatorViewHolder {

//        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.view_adjudicator, parent, false)
//
//        return binding.root

        val inflater = LayoutInflater.from(parent.context)
        val binding = ViewAdjudicatorBinding.inflate(inflater)

        return AdjudicatorViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AdjudicatorViewHolder, position: Int) = holder.bind(adjudicators[position])

    override fun getItemCount(): Int = adjudicators.size

    inner class AdjudicatorViewHolder(val binding: ViewAdjudicatorBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(adjudicator: Adjudicator) {
            val viewModel = AdjudicatorViewModel(adjudicator)

            binding.adjudicatorName.text = viewModel.name
            binding.adjudicatorLicenses.text = viewModel.licenses

            Picasso.get()
                    .load(viewModel.avatarUri)
                    .placeholder(AvatarPlaceholder(viewModel.name, AvatarPlaceholder.DEFAULT_PLACEHOLDER_STRING))
                    .resize(dpToPx(50), dpToPx(50))
                    .centerCrop()
                    .into(binding.adjudicatorAvatarView)
        }
    }
}
