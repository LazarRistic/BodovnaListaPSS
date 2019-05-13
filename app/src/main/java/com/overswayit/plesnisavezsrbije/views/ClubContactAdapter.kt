package com.overswayit.plesnisavezsrbije.views

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.RecyclerView
import com.overswayit.plesnisavezsrbije.App
import com.overswayit.plesnisavezsrbije.R
import com.overswayit.plesnisavezsrbije.activities.ClubActivity
import com.overswayit.plesnisavezsrbije.views.ClubContactAdapter.ClubContactViewModel
import com.overswayit.plesnisavezsrbije.databinding.ViewClubContactItemBinding
import com.overswayit.plesnisavezsrbije.models.ClubContact
import com.overswayit.plesnisavezsrbije.models.ClubContactType
import com.overswayit.plesnisavezsrbije.utils.StringUtil
import java.util.*

/**
 * Created by lazarristic on 18/04/2019.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
class ClubContactAdapter(private val clubContacts: ArrayList<ClubContact>) : RecyclerView.Adapter<ClubContactViewModel>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClubContactViewModel {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ViewClubContactItemBinding.inflate(inflater)

        return ClubContactViewModel(binding)
    }

    override fun onBindViewHolder(holder: ClubContactViewModel, position: Int) {
        holder.bind(clubContacts[position])
    }

    override fun getItemCount(): Int {
        return clubContacts.size
    }

    inner class ClubContactViewModel(val binding: ViewClubContactItemBinding) : RecyclerView.ViewHolder(binding.root) {

        private var textView: TextView? = null
        private var icon1: ImageView? = null
        private var icon2: ImageView? = null

        private var clubContact: ClubContact? = null

        fun bind(clubContact: ClubContact) {
            textView = binding.textView
            icon1 = binding.firstImageView
            icon2 = binding.secondImageView

            icon1!!.setOnClickListener { view -> onFirstItemClicked(view) }
            icon2!!.setOnClickListener { view -> onSecondItemClicked(view) }

            setClubContact(clubContact)
        }

        private fun setClubContact(clubContact: ClubContact) {
            this.clubContact = clubContact
            this.textView!!.text = clubContact.contact
            this.icon2!!.visibility = View.GONE

            when (clubContact.type) {
                ClubContactType.EMAIL, ClubContactType.ADDRESS, ClubContactType.LAND_LINE -> if (clubContact.contacts != null && clubContact.contacts!!.isNotEmpty()) {
                    icon1!!.setImageDrawable(App.getContext().getDrawable(clubContact.contacts!![0].second))
                    icon1!!.visibility = View.VISIBLE
                }
                ClubContactType.MOBILE -> if (clubContact.contacts != null && clubContact.contacts!!.isNotEmpty()) {
                    icon2!!.setImageDrawable(App.getContext().getDrawable(clubContact.contacts!![0].second))
                    icon1!!.setImageDrawable(App.getContext().getDrawable(clubContact.contacts!![1].second))
                    icon2!!.visibility = View.VISIBLE
                }
            }
        }

        private fun onFirstItemClicked(view: View) {
            when (clubContact!!.type) {
                ClubContactType.EMAIL -> onEmailClicked(view)
                ClubContactType.ADDRESS -> onAddressClicked(view)
                ClubContactType.LAND_LINE -> onCallClicked(view)
                ClubContactType.MOBILE -> onMobileMessageClicked(view)
            }
        }

        private fun onSecondItemClicked(view: View) {
            when (clubContact!!.type) {
                ClubContactType.MOBILE -> onCallClicked(view)
                ClubContactType.EMAIL, ClubContactType.ADDRESS, ClubContactType.LAND_LINE -> {
                }
            }
        }

        private fun onEmailClicked(view: View) {
            val emailIntent = Intent(Intent.ACTION_SEND)

            emailIntent.type = "plain/text"
            emailIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf(clubContact!!.contact))
            view.context.startActivity(Intent.createChooser(emailIntent, StringUtil.getString(R.string.send_email)))
        }

        private fun onAddressClicked(view: View) {
            val gmmIntentUri = Uri.parse("geo:0,0?q=" + clubContact!!.contact)
            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            mapIntent.setPackage("com.google.android.apps.maps")
            view.context.startActivity(mapIntent)
        }

        private fun onCallClicked(view: View) {
            val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "Your Phone_number"))
            if (ActivityCompat.checkSelfPermission(view.context, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                view.context.startActivity(intent)
            } else {
                ActivityCompat.requestPermissions(view.context as Activity, arrayOf(Manifest.permission.CALL_PHONE), ClubActivity.CALL_REQUEST)
            }
        }

        private fun onMobileMessageClicked(view: View) {
            val sendIntent = Intent(Intent.ACTION_VIEW)
            sendIntent.data = Uri.parse("sms:" + clubContact!!.contact)
            view.context.startActivity(sendIntent)
        }
    }
}
