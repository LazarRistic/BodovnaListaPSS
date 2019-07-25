package com.overswayit.plesnisavezsrbije.viewmodels

import com.overswayit.plesnisavezsrbije.models.CompetitionEvent
import com.overswayit.plesnisavezsrbije.models.FederationDanceType
import com.overswayit.plesnisavezsrbije.utils.CompetitionsUtil
import java.lang.StringBuilder

/**
 * Created by lazarristic on 2019-07-24.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
class CompetitionEventViewModel(private val competitionEvent: CompetitionEvent, danceType: FederationDanceType) {

    val date = CompetitionsUtil.getDateForUI(competitionEvent.date)
    val organiser = competitionEvent.organiser
    val location = CompetitionsUtil.getLocationForUI(competitionEvent.town, competitionEvent.gym)
    val competitions = CompetitionsUtil.getCompetitionForDanceType(competitionEvent, danceType)
}