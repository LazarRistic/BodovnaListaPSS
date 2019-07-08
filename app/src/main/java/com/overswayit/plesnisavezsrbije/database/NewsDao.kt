package com.overswayit.plesnisavezsrbije.database

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.overswayit.plesnisavezsrbije.models.News

/**
 * Created by lazarristic on 2019-07-04.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
@Dao
interface NewsDao {
    @Query("SELECT * FROM news")
    fun getAll() : LiveData<List<News>>

    @Query("SELECT * FROM news")
    fun getAllPaged(): DataSource.Factory<Int, News>

    @Insert
    fun insertAll(vararg news: News)

    @Delete
    fun delete(vararg news: News)

    @Transaction
    fun deleteAndInsertAll(oldNews: List<News>, newNews: List<News>) {
        oldNews.forEach {
            delete(it)
        }

        newNews.forEach {
            insertAll(it)
        }
    }
}