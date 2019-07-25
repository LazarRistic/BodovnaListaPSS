package com.overswayit.plesnisavezsrbije.views

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.overswayit.plesnisavezsrbije.databinding.ViewClubBinding
import com.overswayit.plesnisavezsrbije.databinding.ViewCompetitionItemBinding
import com.overswayit.plesnisavezsrbije.models.Club
import com.overswayit.plesnisavezsrbije.models.League
import com.overswayit.plesnisavezsrbije.viewmodels.CompetitionEventViewModel
import com.overswayit.plesnisavezsrbije.viewmodels.CompetitionViewModel

/**
 * Created by lazarristic on 2019-07-24.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
class CompetitionsEventsAdapter(private val viewModels: List<CompetitionEventViewModel>) : RecyclerView.Adapter<CompetitionsEventsAdapter.CompetitionEventViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompetitionEventViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ViewCompetitionItemBinding.inflate(inflater)

        return CompetitionEventViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CompetitionEventViewHolder, position: Int) {
        holder.bind(viewModels[position])
    }

    override fun getItemCount(): Int {
        return viewModels.size
    }

    inner class CompetitionEventViewHolder(val binding: ViewCompetitionItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(viewModel: CompetitionEventViewModel) {
            binding.viewModel = viewModel

            viewModel.competitions.forEach {
                when (it.leagueName) {
                    League.SKOLSKA_I_PRVA -> {
                        binding.schoolAndFirstLeague.visibility = View.VISIBLE
                        binding.schoolAndFirstLeague.setViewModel(CompetitionViewModel(it))
                    }

                    League.SUPER -> {
                        binding.superLeague.visibility = View.VISIBLE
                        binding.superLeague.setViewModel(CompetitionViewModel(it))
                    }
                }
            }
        }
    }
}