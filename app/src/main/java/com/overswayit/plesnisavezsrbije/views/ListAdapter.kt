package com.overswayit.plesnisavezsrbije.views

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.overswayit.plesnisavezsrbije.R
import com.overswayit.plesnisavezsrbije.databinding.ViewListItemBinding

/**
 * Created by lazarristic on 2019-05-22.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
class ListAdapter : RecyclerView.Adapter<ListAdapter.ListViewHolder>() {

    private var viewInteractionListener: ViewInteractionListener? = null

    interface ViewInteractionListener {
        fun openListActivity(pair: Pair<Int, Int>)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ViewListItemBinding.inflate(inflater)

        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(getParamsForHolder(position))
    }

    override fun getItemCount(): Int {
        return 3
    }

    fun setViewInteractionListener(viewInteractionListener: ViewInteractionListener) {
        this.viewInteractionListener = viewInteractionListener
    }

    private fun getParamsForHolder(position: Int): Pair<Int, Int> {
        return when (position) {
            0 -> Pair(R.string.point_list, R.drawable.point_list_image)
            1 -> Pair(R.string.rating_list, R.drawable.rating_list_image)
            2 -> Pair(R.string.favorite_couples, R.drawable.favorite_couples_image)
            else -> Pair(R.string.favorite_couples, R.drawable.primary_gradient_color)
        }
    }

    inner class ListViewHolder(val binding: ViewListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(pair: Pair<Int, Int>) {
//            binding.listTitle.text = StringUtil.getString(pair.first)
            binding.listImage.background = binding.root.context.getDrawable(pair.second)
            binding.root.setOnClickListener { viewInteractionListener?.openListActivity(pair) }
//            binding.listImage = pair.second
        }
    }
}