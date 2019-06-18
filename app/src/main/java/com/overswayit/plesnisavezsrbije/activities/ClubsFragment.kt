package com.overswayit.plesnisavezsrbije.activities

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.overswayit.plesnisavezsrbije.MainActivity
import com.overswayit.plesnisavezsrbije.R
import com.overswayit.plesnisavezsrbije.events.EventBus
import com.overswayit.plesnisavezsrbije.events.ViewMesssages
import com.overswayit.plesnisavezsrbije.models.Club
import com.overswayit.plesnisavezsrbije.viewmodels.ClubsViewModel
import com.overswayit.plesnisavezsrbije.views.ClubsAdapter
import com.squareup.otto.Subscribe
import java.util.ArrayList


class ClubsFragment : BaseFragment() {

    private val MY_PERMISSIONS_REQUEST_CALL_PHONE = 0

    private var clubsAdapter: ClubsAdapter? = null
    private val clubList = ArrayList<Club>()

    override fun onResume() {
        super.onResume()
        EventBus.register(this)
    }

    override fun onPause() {
        super.onPause()
        EventBus.unregister(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding: com.overswayit.plesnisavezsrbije.databinding.FragmentClubsBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_clubs, container, false)

        val toolbar = binding.toolbar
        (activity as MainActivity).setSupportActionBar(toolbar)
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back)
        toolbar.setNavigationOnClickListener { activity?.onBackPressed() }
        toolbar.setTitle(R.string.clubs)

        clubsAdapter = ClubsAdapter(clubList)
        clubsAdapter!!.setViewInteractionListener(object : ClubsAdapter.ViewInteractionListener {
            override fun openClubActivity(club: Club) {
                this@ClubsFragment.openClubActivity(club)
            }
        })

        val layoutManager = LinearLayoutManager(activity)
        binding.clubsRecyclerView.layoutManager = layoutManager
        binding.clubsRecyclerView.itemAnimator = DefaultItemAnimator()
        binding.clubsRecyclerView.adapter = clubsAdapter

        val viewModel = ViewModelProviders.of(this).get(ClubsViewModel::class.java)
        viewModel.allClubs.observe(this, Observer { clubs ->
            clubList.clear()
            clubList.addAll(clubs)
            clubsAdapter!!.notifyDataSetChanged()
        })

        if (activity?.checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity!!,
                    arrayOf(Manifest.permission.CALL_PHONE),
                    MY_PERMISSIONS_REQUEST_CALL_PHONE)
        }

        return binding.root
    }

    @Subscribe
    fun on(message: ViewMesssages.ClubContactEmail) {
        val intent = Intent(Intent.ACTION_SENDTO)
        intent.data = Uri.parse("mailto:") // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, message.content)
        intent.putExtra(Intent.EXTRA_SUBJECT, R.string.pps_app)

        if (intent.resolveActivity(activity?.packageManager!!) != null) {
            startActivity(intent)
        }
    }

    @Subscribe
    fun on(message: ViewMesssages.ClubContactPhoneCall) {
        val callIntent = Intent(Intent.ACTION_CALL)
        callIntent.data = Uri.parse("tel:" + message.content)
        if (activity?.checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity!!,
                    arrayOf(Manifest.permission.CALL_PHONE),
                    MY_PERMISSIONS_REQUEST_CALL_PHONE)
            return
        }

        startActivity(callIntent)
    }

    @Subscribe
    fun on(message: ViewMesssages.ClubContactPhoneMessage) {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.fromParts("sms", message.content, null)))
    }

    private fun openClubActivity(club: Club) {
        val intent = Intent(activity, ClubActivity::class.java)
        intent.putExtra(ClubActivity.CLUB_ID_KEY, club.id)
        startActivity(intent)
    }


}
