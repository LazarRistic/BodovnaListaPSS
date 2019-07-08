package com.overswayit.plesnisavezsrbije.views

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.paging.PagedList
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.overswayit.plesnisavezsrbije.databinding.ViewNewsBinding
import com.overswayit.plesnisavezsrbije.models.News

/**
 * Created by lazarristic on 18/02/2019.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
class NewsAdapter : PagedListAdapter<News, NewsAdapter.NewsViewHolder>(DIFF_CALLBACK) {

    companion object {
        var DIFF_CALLBACK: DiffUtil.ItemCallback<News> = object : DiffUtil.ItemCallback<News>() {
            override fun areItemsTheSame(@NonNull oldItem: News, @NonNull newItem: News): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(@NonNull oldItem: News, @NonNull newItem: News): Boolean {
                return oldItem.content == newItem.content
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ViewNewsBinding.inflate(inflater)

        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val news: News? = getItem(position)

        if (news != null) {
            holder.bind(news)
        } else {
            holder.clear()
        }
    }

    inner class NewsViewHolder(private val biding: ViewNewsBinding) : RecyclerView.ViewHolder(biding.root) {

        private lateinit var newsContent: TextView
        private lateinit var newsReadMore: TextView
        private lateinit var newsReadLess: TextView

        fun bind(news: News) {
            newsContent = biding.newsContent
            newsReadMore = biding.newsReadMore
            newsReadLess = biding.newsReadLess

            biding.news = news

            newsReadMore.setOnClickListener { onReadMore() }
            newsReadLess.setOnClickListener { onReadLess() }
        }

        fun clear() {
            val news = News(999999, "", "", "")
            biding.news = news
            onReadLess()
        }

        private fun onReadMore() {
            newsContent.maxLines = Integer.MAX_VALUE
            newsReadMore.visibility = View.GONE
            newsReadLess.visibility = View.VISIBLE
        }

        private fun onReadLess() {
            newsContent.maxLines = 3
            newsReadLess.visibility = View.GONE
            newsReadMore.visibility = View.VISIBLE
        }
    }
}
