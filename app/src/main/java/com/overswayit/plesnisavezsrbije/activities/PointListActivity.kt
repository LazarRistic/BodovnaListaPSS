package com.overswayit.plesnisavezsrbije.activities

import android.app.SearchManager
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.android.material.tabs.TabLayout
import com.overswayit.plesnisavezsrbije.MyApp
import com.overswayit.plesnisavezsrbije.R
import com.overswayit.plesnisavezsrbije.databinding.ActivityPointListBinding
import com.overswayit.plesnisavezsrbije.fragments.PointListAdapter
import kotlinx.android.synthetic.main.activity_point_list.*

class PointListActivity : BaseActivity() {

    private var queryTextListener: SearchView.OnQueryTextListener? = null
    private var searchView: SearchView? = null

    private val searchQueryObserver = MutableLiveData<String>()

    interface OnSearchQueryListener {
        fun onQueryChanged(query: String)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityPointListBinding = DataBindingUtil.setContentView(this, R.layout.activity_point_list)

        val toolbar = binding.toolbar
        setSupportActionBar(toolbar)
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back)
        toolbar.setNavigationOnClickListener { onBackPressed() }
        toolbar.setTitle(R.string.point_list)

        binding.tabLayout.addTab(tabLayout!!.newTab().setText(getString(R.string.latin)))
        tabLayout.addTab(tabLayout!!.newTab().setText(getString(R.string.standard)))
        tabLayout.tabGravity = TabLayout.GRAVITY_FILL
        tabLayout.setTabTextColors(resources.getColor(R.color.white90, null), resources.getColor(R.color.white, null))

        val adapter = PointListAdapter(supportFragmentManager, tabLayout!!.tabCount)
        binding.viewPager.adapter = adapter
        binding.viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
        tabLayout!!.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab != null) {
                    binding.viewPager.currentItem = tab.position
                }
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                //Ignored
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                //Ignored
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.search_filter_menu, menu)

        searchView = menu.findItem(R.id.search).actionView as SearchView
        searchView!!.findViewById<EditText>(androidx.appcompat.R.id.search_src_text).setTextColor(Color.WHITE)
        val imageView: ImageView = searchView!!.findViewById(androidx.appcompat.R.id.search_close_btn)
        imageView.imageTintList = androidx.databinding.adapters.Converters.convertColorToColorStateList(MyApp.applicationContext().getColor(R.color.white))

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager

        searchView?.apply {
            setSearchableInfo(searchManager.getSearchableInfo(componentName))
        }

        queryTextListener = object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String): Boolean {
                setSearchQuery(newText)
                return true
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                setSearchQuery(query)
                return true
            }
        }

        searchView?.setOnQueryTextListener(queryTextListener)

        return true
    }

    fun searchQuery(): LiveData<String> {
        return searchQueryObserver
    }

    private fun setSearchQuery(query: String) {
        searchQueryObserver.postValue(query)
    }
}
