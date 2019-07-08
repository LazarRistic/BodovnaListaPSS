package com.overswayit.plesnisavezsrbije.activities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.overswayit.plesnisavezsrbije.MainActivity
import com.overswayit.plesnisavezsrbije.R
import com.overswayit.plesnisavezsrbije.models.News
import com.overswayit.plesnisavezsrbije.viewmodels.NewsViewModel
import com.overswayit.plesnisavezsrbije.views.NewsAdapter
import java.util.*

class NewsFragment : BaseFragment() {
    private lateinit var binding: com.overswayit.plesnisavezsrbije.databinding.FragmentNewsBinding
    private lateinit var newsAdapter: NewsAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_news, container, false)

        val newsRecyclerView = binding.newsRecyclerView

        (activity as MainActivity).setSupportActionBar(binding.toolbar)

        setupActionBar()

        newsAdapter = NewsAdapter()
        val mLayoutManager = LinearLayoutManager(activity)
        newsRecyclerView.layoutManager = mLayoutManager
        newsRecyclerView.itemAnimator = DefaultItemAnimator()
        newsRecyclerView.adapter = newsAdapter

        val viewModel = ViewModelProviders.of(this).get(NewsViewModel::class.java)
        viewModel.allNews().observe(this, Observer(newsAdapter::submitList))

        return binding.root
    }

    private fun setupActionBar() {
        val actionBar = activity?.actionBar

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu)
        }
    }
}
