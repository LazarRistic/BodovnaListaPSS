package com.overswayit.plesnisavezsrbije.views

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.overswayit.plesnisavezsrbije.R
import com.overswayit.plesnisavezsrbije.databinding.ViewClubBinding
import com.overswayit.plesnisavezsrbije.models.Club
import com.overswayit.plesnisavezsrbije.utils.ClubUtil
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

/**
 * Created by lazarristic on 19/02/2019.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
class ClubsAdapter(private val clubList: List<Club>) : RecyclerView.Adapter<ClubsAdapter.ClubsViewHolder>() {

    private var viewInteractionListener: ViewInteractionListener? = null

    interface ViewInteractionListener {
        fun openClubActivity(club: Club)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClubsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ViewClubBinding.inflate(inflater)

        return ClubsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ClubsViewHolder, position: Int) {
        holder.bind(clubList[position])

    }

    override fun getItemCount(): Int {
        return clubList.size
    }

    fun setViewInteractionListener(viewInteractionListener: ViewInteractionListener) {
        this.viewInteractionListener = viewInteractionListener
    }

    inner class ClubsViewHolder(val biding: ViewClubBinding) : RecyclerView.ViewHolder(biding.root) {
        fun bind(club: Club) {
            Picasso.get()
                    .load(club.logoUrl)
                    .error(R.drawable.ic_photo)
                    .into(biding.clubLogo, object : Callback {
                        override fun onSuccess() {
                            biding.clubLogo.visibility = View.VISIBLE
                        }

                        override fun onError(e: Exception) {

                        }
                    })

            biding.clubName.text = ClubUtil.getClubNameAndTown(club)
            biding.clubContact.text = club.contactName

            biding.rootView.setOnClickListener { viewInteractionListener?.openClubActivity(club) }
        }
    }
}
