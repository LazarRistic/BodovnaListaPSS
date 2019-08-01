package com.overswayit.plesnisavezsrbije.viewmodels

import android.text.TextUtils
import android.view.View
import androidx.annotation.ColorRes
import com.overswayit.plesnisavezsrbije.MyApp
import com.overswayit.plesnisavezsrbije.R
import com.overswayit.plesnisavezsrbije.models.*
import com.overswayit.plesnisavezsrbije.utils.CoupleUtil
import com.overswayit.plesnisavezsrbije.utils.StringUtil

/**
 * Created by lazarristic on 2019-06-07.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
class ListItemViewModel(val couplesListItem: CouplesListItem) : ListItemViewModelProtocol {

    @ColorRes
    override var colorOfBorder: Int = getColorBorder()

    override val couplesNames: String
        get() {
            val couple = getCouple()

            if (couple != null) {
                return CoupleUtil.getCoupleLastNames(couple)
            }

            return StringUtil.getString(R.string.not_available)
        }

    override val avatarUrl: String
        get() {
            val club = getClub()

            if (club != null) {
                return club.logoUrl
            }

            return StringUtil.getString(R.string.not_available)
        }

    override val place: String
        get() = couplesListItem.place.toString()

    override val points: String
        get() = couplesListItem.points.toString()

    override val danceCategoryVisibility: Int
        get() {
            return when (couplesListItem.listType) {
                ListType.POINT -> View.VISIBLE
                else -> View.GONE
            }
        }

    override val ageCategoryColor: Int
        get() {
            return MyApp.applicationContext().getColor(getAgeCategoryColorAsResource())
        }

    override val danceCategoryColor: Int
        get() {
            return when (couplesListItem.listType) {
                ListType.POINT -> MyApp.applicationContext().getColor(getDanceCategoryColorAsResource())
                else -> MyApp.applicationContext().getColor(R.color.colorPrimary)
            }
        }

    fun shouldLoadLogoUrl(): Boolean {
        val club = getClub()

        if (club != null && !TextUtils.isEmpty(club.logoUrl)) {
            return true
        }

        return false
    }

    fun getAgeCategory(): String {
        return couplesListItem.ageCategory.asString()
    }

    fun getDanceCategory(): String {
        return when (couplesListItem.listType) {
            ListType.POINT -> (couplesListItem as PointListItem).danceCategory.toString()
            else -> DanceCategory.A.asString()
        }
    }

    @ColorRes
    private fun getColorBorder(): Int {
        return when (couplesListItem.listType) {
            ListType.POINT -> getDanceCategoryColorAsResource()
            else -> R.color.colorPrimary
        }
    }

    private fun getCouple(): Couple? {
        return couplesListItem.couple
    }

    private fun getClub(): Club? {
        val couple = getCouple()

        if (couple != null) {
            return couple.club
        }

        return null
    }

    private fun getDanceCategoryColorAsResource(): Int {
        return CoupleUtil.getDanceCategoryColor((couplesListItem as PointListItem).danceCategory)
    }

    private fun getAgeCategoryColorAsResource(): Int {
        return CoupleUtil.getAgeCategoryColor(couplesListItem.ageCategory)
    }
}