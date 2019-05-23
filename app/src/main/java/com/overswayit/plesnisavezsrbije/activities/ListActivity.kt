package com.overswayit.plesnisavezsrbije.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.overswayit.plesnisavezsrbije.R
import com.overswayit.plesnisavezsrbije.databinding.ActivityListBinding
import com.overswayit.plesnisavezsrbije.views.ListAdapter
import kotlinx.android.synthetic.main.activity_adjudicators.toolbar
import kotlinx.android.synthetic.main.activity_list.*


class ListActivity : AppCompatActivity() {

    private lateinit var listAdapter: ListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_list)
        val binding: ActivityListBinding = DataBindingUtil.setContentView(this, R.layout.activity_list)

        setSupportActionBar(binding.toolbar)
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back)
        toolbar.setNavigationOnClickListener { onBackPressed() }
        toolbar.setTitle(R.string.list)

        listAdapter = ListAdapter()
        listAdapter.setViewInteractionListener(object : ListAdapter.ViewInteractionListener {
            override fun openListActivity(pair: Pair<Int, Int>) {
                this@ListActivity.openListActivity(pair)
            }
        })

        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.listRecyclerView.layoutManager = layoutManager
        listRecyclerView!!.itemAnimator = DefaultItemAnimator()
        listRecyclerView!!.adapter = listAdapter
    }

    private fun openListActivity(pair: Pair<Int, Int>) {
        val intent = Intent(this, getActivityFromTitle(pair.first))
        startActivity(intent)
    }

    private fun getActivityFromTitle(stringRes: Int): Class<*>? {
        return when (stringRes) {
            R.string.point_list -> ClubsActivity::class.java
            R.string.rating_list -> ClubsActivity::class.java
            R.string.favorite_couples -> ClubsActivity::class.java
            else -> ClubsActivity::class.java
        }
    }
}
