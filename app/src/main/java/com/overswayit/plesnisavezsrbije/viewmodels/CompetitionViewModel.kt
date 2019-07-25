package com.overswayit.plesnisavezsrbije.viewmodels

import android.view.View
import com.overswayit.plesnisavezsrbije.R
import com.overswayit.plesnisavezsrbije.models.Competition
import com.overswayit.plesnisavezsrbije.utils.CompetitionsUtil
import com.overswayit.plesnisavezsrbije.utils.StringUtil.getString

/**
 * Created by lazarristic on 2019-07-24.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
class CompetitionViewModel(private val competition: Competition) {
    val title = competition.leagueName
    val categories = CompetitionsUtil.getCompetitionCategories(competition.category)

    val doc1Url: String
        get() {
            return if (competition.docs.isNullOrEmpty()) {
                getString(R.string.not_available)
            } else {
                competition.docs[0]
            }
        }

    val doc2Url: String
        get() {
            return if (competition.docs.size > 1) {
                competition.docs[1]
            } else {
                getString(R.string.not_available)
            }
        }

    fun doc1Visibility(): Int {
        return if (competition.docs.isNullOrEmpty()) {
            View.GONE
        } else {
            View.VISIBLE
        }
    }

    fun doc2Visibility(): Int {
        return if (competition.docs.size > 1) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }
}