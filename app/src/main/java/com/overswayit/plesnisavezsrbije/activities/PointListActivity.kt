package com.overswayit.plesnisavezsrbije.activities

import android.app.SearchManager
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.SwitchCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import com.google.android.material.tabs.TabLayout
import com.overswayit.plesnisavezsrbije.MyApp
import com.overswayit.plesnisavezsrbije.R
import com.overswayit.plesnisavezsrbije.databinding.ActivityPointListBinding
import com.overswayit.plesnisavezsrbije.fragments.PointListAdapter
import com.overswayit.plesnisavezsrbije.models.AgeCategory
import com.overswayit.plesnisavezsrbije.models.DanceCategory
import com.overswayit.plesnisavezsrbije.repository.FilterRepository
import kotlinx.android.synthetic.main.activity_point_list.*

class PointListActivity : BaseActivity() {

    private var queryTextListener: SearchView.OnQueryTextListener? = null
    private var searchView: SearchView? = null

    private val searchQueryObserver = MutableLiveData<String>()

    interface OnSearchQueryListener {
        fun onQueryChanged(query: String)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.filter -> {
                showDialog()
            }
        }

        return true
    }

    private fun showDialog() {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.filters, null)
        val builder = AlertDialog.Builder(this, R.style.AlertDialogStyle)
                .setView(dialogView)
                .setTitle("Filteri")
                .setPositiveButton(R.string.ok) { dialog, _ -> dialog?.dismiss() }

        val filterRepo = FilterRepository(application)

        val seniorSwitch = dialogView.findViewById<SwitchCompat>(R.id.seniorSwitch)
        val adultSwitch = dialogView.findViewById<SwitchCompat>(R.id.adultSwitch)
        val youthSwitch = dialogView.findViewById<SwitchCompat>(R.id.youthSwitch)
        val juvenileSwitch = dialogView.findViewById<SwitchCompat>(R.id.juvenileSwitch)
        val juniorIISwitch = dialogView.findViewById<SwitchCompat>(R.id.juniorIISwitch)
        val juniorISwitch = dialogView.findViewById<SwitchCompat>(R.id.juniorISwitch)
        val iSwitch = dialogView.findViewById<SwitchCompat>(R.id.iSwitch)
        val aSwitch = dialogView.findViewById<SwitchCompat>(R.id.aSwitch)
        val bSwitch = dialogView.findViewById<SwitchCompat>(R.id.bSwitch)
        val cSwitch = dialogView.findViewById<SwitchCompat>(R.id.cSwitch)
        val dSwitch = dialogView.findViewById<SwitchCompat>(R.id.dSwitch)

        lifecycleScope.launchWhenCreated {
            val filters = filterRepo.getActiveFilters()

            seniorSwitch.isChecked = filters.contains(AgeCategory.ADULT.asString())
            adultSwitch.isChecked = filters.contains(AgeCategory.SENIOR.asString())
            youthSwitch.isChecked = filters.contains(AgeCategory.YOUTH.asString())
            juniorIISwitch.isChecked = filters.contains(AgeCategory.JUNIOR_II.asString())
            juniorISwitch.isChecked = filters.contains(AgeCategory.JUNIOR_I.asString())
            juvenileSwitch.isChecked = filters.contains(AgeCategory.JUVENILE.asString())
            iSwitch.isChecked = filters.contains(DanceCategory.I.asString())
            aSwitch.isChecked = filters.contains(DanceCategory.A.asString())
            bSwitch.isChecked = filters.contains(DanceCategory.B.asString())
            cSwitch.isChecked = filters.contains(DanceCategory.C.asString())
            dSwitch.isChecked = filters.contains(DanceCategory.D.asString())
        }

        seniorSwitch.setOnCheckedChangeListener { _, isChecked -> lifecycleScope.launchWhenCreated { filterRepo.changeFilter(AgeCategory.SENIOR.asString(), isChecked) } }
        adultSwitch.setOnCheckedChangeListener { _, isChecked -> lifecycleScope.launchWhenCreated { filterRepo.changeFilter(AgeCategory.ADULT.asString(), isChecked) } }
        youthSwitch.setOnCheckedChangeListener { _, isChecked -> lifecycleScope.launchWhenCreated { filterRepo.changeFilter(AgeCategory.YOUTH.asString(), isChecked) } }
        juvenileSwitch.setOnCheckedChangeListener { _, isChecked -> lifecycleScope.launchWhenCreated { filterRepo.changeFilter(AgeCategory.JUVENILE.asString(), isChecked) } }
        juniorIISwitch.setOnCheckedChangeListener { _, isChecked -> lifecycleScope.launchWhenCreated { filterRepo.changeFilter(AgeCategory.JUNIOR_II.asString(), isChecked) } }
        juniorISwitch.setOnCheckedChangeListener { _, isChecked -> lifecycleScope.launchWhenCreated { filterRepo.changeFilter(AgeCategory.JUNIOR_I.asString(), isChecked) } }
        iSwitch.setOnCheckedChangeListener { _, isChecked -> lifecycleScope.launchWhenCreated { filterRepo.changeFilter(DanceCategory.I.asString(), isChecked) } }
        aSwitch.setOnCheckedChangeListener { _, isChecked -> lifecycleScope.launchWhenCreated { filterRepo.changeFilter(DanceCategory.A.asString(), isChecked) } }
        bSwitch.setOnCheckedChangeListener { _, isChecked -> lifecycleScope.launchWhenCreated { filterRepo.changeFilter(DanceCategory.B.asString(), isChecked) } }
        cSwitch.setOnCheckedChangeListener { _, isChecked -> lifecycleScope.launchWhenCreated { filterRepo.changeFilter(DanceCategory.C.asString(), isChecked) } }
        dSwitch.setOnCheckedChangeListener { _, isChecked -> lifecycleScope.launchWhenCreated { filterRepo.changeFilter(DanceCategory.D.asString(), isChecked) } }

        builder.show()
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
