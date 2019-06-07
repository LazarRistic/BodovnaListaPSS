package com.overswayit.plesnisavezsrbije.views

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.overswayit.plesnisavezsrbije.R
import com.overswayit.plesnisavezsrbije.databinding.ViewCoupleListItemBinding
import com.overswayit.plesnisavezsrbije.models.PointListItem
import com.overswayit.plesnisavezsrbije.viewmodels.ListItemViewModel
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso


/**
 * Created by lazarristic on 2019-05-23.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
class PointListItemAdapter(private val pointList: List<ListItemViewModel>) : RecyclerView.Adapter<PointListItemAdapter.PointListViewHolder>() {

    private var viewInteractionListener: ViewInteractionListener? = null

    interface ViewInteractionListener {
        fun openCoupleActivity(pointListItem: PointListItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PointListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ViewCoupleListItemBinding.inflate(inflater)

        return PointListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PointListViewHolder, position: Int) {
        holder.bind(pointList[position])
    }

    override fun getItemCount(): Int {
        return pointList.size
    }

    fun setViewInteractionListener(viewInteractionListener: ViewInteractionListener) {
        this.viewInteractionListener = viewInteractionListener
    }

    inner class PointListViewHolder(val binding: ViewCoupleListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(pointListItemViewModel: ListItemViewModel) {

            binding.viewModel = pointListItemViewModel

            loadClubLogo(pointListItemViewModel.shouldLoadLogoUrl(), pointListItemViewModel.avatarUrl)
            binding.avatarView.setBorderColor(getBorderColor(pointListItemViewModel.colorOfBorder))
        }

        private fun loadClubLogo(shouldLoadLogoUrl: Boolean, avatarUrl: String) {
            if (shouldLoadLogoUrl) {
                Picasso.get()
                        .load(avatarUrl)
                        .error(R.drawable.ic_photo)
                        .into(binding.avatarView, object : Callback {
                            override fun onSuccess() {
                                binding.avatarView.visibility = View.VISIBLE
                            }

                            override fun onError(ignored: Exception?) {}
                        })
            }
        }


        private fun getBorderColor(colorOfBorder: Int): Int {
            return ContextCompat.getColor(binding.root.context, colorOfBorder)
        }
    }
}