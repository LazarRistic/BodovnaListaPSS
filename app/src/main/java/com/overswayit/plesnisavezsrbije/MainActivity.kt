package com.overswayit.plesnisavezsrbije

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.navigation.NavigationView
import com.overswayit.plesnisavezsrbije.activities.AdjudicatorsActivity
import com.overswayit.plesnisavezsrbije.activities.BaseActivity
import com.overswayit.plesnisavezsrbije.activities.ClubsActivity
import com.overswayit.plesnisavezsrbije.activities.ListActivity
import com.overswayit.plesnisavezsrbije.databinding.ActivityMainBinding
import com.overswayit.plesnisavezsrbije.models.News
import com.overswayit.plesnisavezsrbije.viewmodels.NewsViewModel
import com.overswayit.plesnisavezsrbije.views.NewsAdapter
import kotlinx.android.synthetic.main.layout_news.view.*
import java.util.*

class MainActivity : BaseActivity() {

    private lateinit var navigationView: NavigationView
    private var drawerLayout: DrawerLayout? = null
    private var newsAdapter: NewsAdapter? = null
    private val newsArrayList = ArrayList<News>()

    override fun onResume() {
        super.onResume()
        navigationView.let { navigationView.setCheckedItem(R.id.nav_news) }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        drawerLayout = binding.drawerLayout
        navigationView = binding.navView
        val newsRecyclerView = binding.included.newsRecyclerView

        setSupportActionBar(binding.toolbar)

        setupActionBar()
        setupNavigationDrawer()

        newsAdapter = NewsAdapter(newsArrayList)
        val mLayoutManager = LinearLayoutManager(applicationContext)
        newsRecyclerView.layoutManager = mLayoutManager
        newsRecyclerView.itemAnimator = DefaultItemAnimator()
        newsRecyclerView.adapter = newsAdapter

        val viewModel = ViewModelProviders.of(this).get(NewsViewModel::class.java)
        viewModel.allNews.observe(this, Observer { news ->
            newsArrayList.clear()
            newsArrayList.addAll(news)
            newsAdapter!!.notifyDataSetChanged()
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                drawerLayout!!.openDrawer(GravityCompat.START)
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }

    private fun setupNavigationDrawer() {
        navigationView.setNavigationItemSelectedListener { menuItem ->
            menuItem.isChecked = true
            drawerLayout!!.closeDrawers()
            navigationItemSelected(menuItem.itemId)

            true
        }

        navigationView.setCheckedItem(R.id.nav_news)
    }

    private fun navigationItemSelected(itemId: Int) {
        when (itemId) {
            R.id.nav_news ->
                //                ToDo: Open News Activity
                Toast.makeText(applicationContext, R.string.news, Toast.LENGTH_SHORT).show()
            R.id.nav_point_list -> openActivity(ListActivity::class.java)
            R.id.nav_rating_list ->
                //                ToDo: Open Rating List Activity
                Toast.makeText(applicationContext, R.string.rating_list, Toast.LENGTH_SHORT).show()
            R.id.nav_favorite_couples ->
                //                ToDo: Open Favorite Couples Activity
                Toast.makeText(applicationContext, R.string.favorite_couples, Toast.LENGTH_SHORT).show()
            R.id.nav_clubs -> openActivity(ClubsActivity::class.java)
            R.id.nav_adjudicators -> openActivity(AdjudicatorsActivity::class.java)
            R.id.nav_competitions ->
                //                ToDo: Open Competitions Activity
                Toast.makeText(applicationContext, R.string.competitions, Toast.LENGTH_SHORT).show()
            R.id.nav_contacts ->
                //                ToDo: Open Contact Activity
                Toast.makeText(applicationContext, R.string.contact, Toast.LENGTH_SHORT).show()
        }
    }

    private fun openActivity(clubsActivityClass: Class<*>) {
        startActivity(Intent(this, clubsActivityClass))
    }

    private fun setupActionBar() {
        val actionBar = supportActionBar

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu)
        }
    }
}
