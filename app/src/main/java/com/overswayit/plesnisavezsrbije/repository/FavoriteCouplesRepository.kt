package com.overswayit.plesnisavezsrbije.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.overswayit.plesnisavezsrbije.database.AppDatabase
import com.overswayit.plesnisavezsrbije.database.FavoriteCoupleDao
import com.overswayit.plesnisavezsrbije.models.Couple
import com.overswayit.plesnisavezsrbije.models.FavoriteCouple
import kotlinx.coroutines.Dispatchers

/**
 * Created by lazarristic on 2019-08-02.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
class FavoriteCouplesRepository(application: Application) : BaseRepository() {
    private val favoriteCoupleDao: FavoriteCoupleDao = AppDatabase.invoke(application.applicationContext).favoriteCoupleDao()

    suspend fun getFavoriteCouples() = liveData(Dispatchers.IO) {
        emitSource(favoriteCoupleDao.getAll())
    }

    suspend fun removeFavoriteCouple(couple: Couple) {
        favoriteCoupleDao.insertOrUpdate(getFavoriteCoupleFromCouple(couple, false))
    }

    suspend fun addFavoriteCouple(couple: Couple) {
        favoriteCoupleDao.insertOrUpdate(getFavoriteCoupleFromCouple(couple, true))
    }

    suspend fun removeFavoriteCouple(favoriteCouple: FavoriteCouple) {
        favoriteCouple.favorite = false
        favoriteCoupleDao.insertOrUpdate(favoriteCouple)
    }

    suspend fun addFavoriteCouple(favoriteCouple: FavoriteCouple) {
        favoriteCouple.favorite = true
        favoriteCoupleDao.insertOrUpdate(favoriteCouple)
    }

    suspend fun isFavorite(couple: Couple) : Boolean {
        val favoriteCouple: FavoriteCouple = favoriteCoupleDao.findFavoriteCoupleByCoupleId(coupleId = couple.id)

        return favoriteCouple?.favorite ?: false
    }

    suspend fun toggleFavorite(couple: Couple) {
        val favoriteCouple: FavoriteCouple = favoriteCoupleDao.findFavoriteCoupleByCoupleId(coupleId = couple.id)

        if (favoriteCouple != null) {
            if (favoriteCouple.favorite) {
                removeFavoriteCouple(favoriteCouple)
            } else {
                addFavoriteCouple(favoriteCouple)
            }
        } else{
            addFavoriteCouple(FavoriteCouple(couple = couple, favorite = true))
        }
    }

    private suspend fun getFavoriteCoupleFromCouple(couple: Couple, favorite: Boolean = true): FavoriteCouple {
        var favoriteCouple: FavoriteCouple = favoriteCoupleDao.findFavoriteCoupleByCoupleId(coupleId = couple.id)

        if (favoriteCouple != null) {
            favoriteCouple.favorite = favorite
        } else {
            favoriteCouple = FavoriteCouple(couple = couple, favorite = favorite)
        }

        return favoriteCouple
    }

    suspend fun findFavoriteCoupleById(couple: Couple): LiveData<Boolean> {
        var favoriteCouple: FavoriteCouple = favoriteCoupleDao.findFavoriteCoupleByCoupleId(couple.id)

        if (favoriteCouple == null) {
            favoriteCouple = FavoriteCouple(couple = couple, favorite = false)
            removeFavoriteCouple(favoriteCouple)
        }

        return favoriteCoupleDao.isFavorite(couple.id)
    }
}