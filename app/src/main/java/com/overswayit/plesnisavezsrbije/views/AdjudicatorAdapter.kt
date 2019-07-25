package com.overswayit.plesnisavezsrbije.views

import android.graphics.drawable.BitmapDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.overswayit.plesnisavezsrbije.MyApp
import com.overswayit.plesnisavezsrbije.R
import com.overswayit.plesnisavezsrbije.databinding.ViewAdjudicatorBinding
import com.overswayit.plesnisavezsrbije.models.Adjudicator
import com.overswayit.plesnisavezsrbije.utils.AdjudicatorsUtil
import com.overswayit.plesnisavezsrbije.utils.DisplayUtil.dpToPx
import com.overswayit.plesnisavezsrbije.utils.DrawUtil
import com.overswayit.plesnisavezsrbije.viewmodels.AdjudicatorViewModel
import com.squareup.picasso.Picasso

/**
 * Created by lazarristic on 21/02/2019.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
class AdjudicatorAdapter(private val adjudicators: List<Adjudicator>) : RecyclerView.Adapter<AdjudicatorAdapter.AdjudicatorViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdjudicatorViewHolder {
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
            binding.adjudicatorName.setTextColor(viewModel.borderColor)
            binding.adjudicatorLicenses.text = viewModel.licenses
            binding.adjudicatorAvatarView.setBorderColor(viewModel.borderColor)

            val bitmap = DrawUtil.writeTextOnBitmap(text = viewModel.initials!!, textColor = viewModel.borderColor, resource = R.drawable.contact_circle_gray)
            val drawable = BitmapDrawable(MyApp.applicationContext().resources, bitmap)

            Picasso.get()
                    .load(viewModel.avatarUri)
                    .placeholder(drawable)
                    .error(drawable)
                    .resize(dpToPx(50), dpToPx(50))
                    .centerCrop()
                    .into(binding.adjudicatorAvatarView)
        }
    }
}
