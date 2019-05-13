package com.overswayit.plesnisavezsrbije.activities

import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager

import com.overswayit.plesnisavezsrbije.R
import com.overswayit.plesnisavezsrbije.databinding.ActivityClubBinding
import com.overswayit.plesnisavezsrbije.utils.ClubUtil
import com.overswayit.plesnisavezsrbije.utils.StringUtil
import com.overswayit.plesnisavezsrbije.viewmodels.ClubViewModel
import com.overswayit.plesnisavezsrbije.viewmodels.ClubViewModelFactory
import com.overswayit.plesnisavezsrbije.views.ClubContactAdapter
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

import kotlinx.android.synthetic.main.activity_club.*

class ClubActivity : AppCompatActivity() {

    private val clubId: Int
        get() = intent.getIntExtra(CLUB_ID_KEY, 0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityClubBinding = DataBindingUtil.setContentView(this, R.layout.activity_club)

        setSupportActionBar(binding.toolbar)
        binding.toolbar.setNavigationIcon(R.drawable.ic_arrow_back)
        toolbar!!.setNavigationOnClickListener { onBackPressed() }
        toolbar!!.setTitle(R.string.clubs)

        val permissions = arrayOf(android.Manifest.permission.CALL_PHONE)
        if (!hasPermissions(this, *permissions)) {
            ActivityCompat.requestPermissions(this, permissions, 1)
        }

        val viewModel = ViewModelProviders.of(this, ClubViewModelFactory(this.application, clubId)).get(ClubViewModel::class.java)
        viewModel.club.observe(this, Observer { club ->
            binding.clubName.text = ClubUtil.getClubNameAndTown(club)
            binding.clubContact.text = club.contactName

            Picasso.get()
                    .load(club.logoUrl)
                    .error(R.drawable.ic_photo)
                    .into(binding.clubLogo, object : Callback {
                        override fun onSuccess() {
                            binding.clubLogo.visibility = View.VISIBLE
                        }

                        override fun onError(e: Exception) {

                        }
                    })

            val contactAdapter = ClubContactAdapter(ClubUtil.getClubContacts(club))

            val layoutManager = LinearLayoutManager(applicationContext)
            binding.contactRecyclerView.layoutManager = layoutManager
            contactRecyclerView!!.itemAnimator = DefaultItemAnimator()
            contactRecyclerView!!.adapter = contactAdapter
        })
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            CALL_REQUEST -> {
                if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    AlertDialog.Builder(this@ClubActivity)
                            .setMessage(R.string.need_call_permission)
                            .setPositiveButton(StringUtil.getString(R.string.ok), null)
                            .create()
                            .show()
                }
            }
        }
    }

    companion object {

        val CALL_REQUEST = 1
        val CLUB_ID_KEY = "club id key"

        private fun hasPermissions(context: Context?, vararg permissions: String): Boolean {
            if (context != null) {
                for (permission in permissions) {
                    if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                        return false
                    }
                }
            }

            return true
        }
    }
}
