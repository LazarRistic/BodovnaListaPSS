package com.overswayit.plesnisavezsrbije.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.overswayit.plesnisavezsrbije.models.CompetitionEvent
import com.overswayit.plesnisavezsrbije.models.FederationDanceType
import com.overswayit.plesnisavezsrbije.networking.CompetitionApiService
import com.overswayit.plesnisavezsrbije.repository.CompetitionRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * Created by lazarristic on 2019-07-24.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
open class CompetitionsEventsViewModel(application: Application, danceType: FederationDanceType) : AndroidViewModel(application) {
    private val competitionRepository: CompetitionRepository = CompetitionRepository(application, CompetitionApiService.competitionsApi)
    private val observableCompetitions = MediatorLiveData<List<CompetitionEventViewModel>>()
    private lateinit var competitionEventLiveData: LiveData<List<CompetitionEventViewModel>>

    init {
        viewModelScope.launch {
            competitionEventLiveData = Transformations.map(competitionRepository.getAll()) { competitions ->
                transformToViewModel(competitions, danceType)
            }

            observableCompetitions.addSource(competitionEventLiveData, observableCompetitions::setValue)

            fetchCompetitions()
        }
    }

    val competitions: LiveData<List<CompetitionEventViewModel>>
        get() = observableCompetitions

    private suspend fun fetchCompetitions() = withContext(Dispatchers.IO) {
        competitionRepository.insertOrUpdate(competitionRepository.getLatestComps())
    }

    private fun transformToViewModel(competitions: List<CompetitionEvent>, danceType: FederationDanceType): List<CompetitionEventViewModel> {
        val list = ArrayList<CompetitionEventViewModel>()

        competitions.forEach {
            list.add(CompetitionEventViewModel(it, danceType))
        }

        return list
    }
}