package com.overswayit.plesnisavezsrbije.activities

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.overswayit.plesnisavezsrbije.R
import com.overswayit.plesnisavezsrbije.databinding.ActivityClubsBinding
import com.overswayit.plesnisavezsrbije.events.EventBus
import com.overswayit.plesnisavezsrbije.events.ViewMesssages
import com.overswayit.plesnisavezsrbije.models.Club
import com.overswayit.plesnisavezsrbije.viewmodels.ClubsViewModel
import com.overswayit.plesnisavezsrbije.views.ClubsAdapter
import com.squareup.otto.Subscribe
import kotlinx.android.synthetic.main.activity_clubs.*
import java.util.*

class ClubsActivity : BaseActivity() {

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityClubsBinding = DataBindingUtil.setContentView(this, R.layout.activity_clubs)

        val toolbar = binding.toolbar
        setSupportActionBar(toolbar)
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back)
        toolbar.setNavigationOnClickListener { onBackPressed() }
        toolbar.setTitle(R.string.clubs)

        clubsAdapter = ClubsAdapter(clubList)
        clubsAdapter!!.setViewInteractionListener(object : ClubsAdapter.ViewInteractionListener {
            override fun openClubActivity(club: Club) {
                this@ClubsActivity.openClubActivity(club)
            }
        })

        val layoutManager = LinearLayoutManager(applicationContext)
        binding.clubsRecyclerView.layoutManager = layoutManager
        clubsRecyclerView!!.itemAnimator = DefaultItemAnimator()
        clubsRecyclerView!!.adapter = clubsAdapter

        val viewModel = ViewModelProviders.of(this).get(ClubsViewModel::class.java)
        viewModel.allClubs.observe(this, Observer { clubs ->
            clubList.clear()
            clubList.addAll(clubs)
            clubsAdapter!!.notifyDataSetChanged()
        })

        if (checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this@ClubsActivity,
                    arrayOf(Manifest.permission.CALL_PHONE),
                    MY_PERMISSIONS_REQUEST_CALL_PHONE)
        }
    }

    @Subscribe
    fun on(message: ViewMesssages.ClubContactEmail) {
        val intent = Intent(Intent.ACTION_SENDTO)
        intent.data = Uri.parse("mailto:") // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, message.content)
        intent.putExtra(Intent.EXTRA_SUBJECT, R.string.pps_app)

        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }

    @Subscribe
    fun on(message: ViewMesssages.ClubContactPhoneCall) {
        val callIntent = Intent(Intent.ACTION_CALL)
        callIntent.data = Uri.parse("tel:" + message.content)
        if (checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this@ClubsActivity,
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
        val intent = Intent(this, ClubActivity::class.java)
        intent.putExtra(ClubActivity.CLUB_ID_KEY, club.id)
        startActivity(intent)
    }
}
