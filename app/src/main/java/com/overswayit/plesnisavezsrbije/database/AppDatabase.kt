package com.overswayit.plesnisavezsrbije.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.overswayit.plesnisavezsrbije.models.*
import com.overswayit.plesnisavezsrbije.utils.ConvertersDB
import java.util.concurrent.Executors

/**
 * Created by lazarristic on 2019-06-25.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
@Database(entities = [Club::class, PointListItem::class, RatingListItem::class, News::class, Adjudicator::class, CompetitionEvent::class, FavoriteCouple::class, Filter::class, LastModified::class], version = 4, exportSchema = false)
@TypeConverters(ConvertersDB::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun clubDao(): ClubDao
    abstract fun pointListDao(): PointListDao
    abstract fun ratingListDao(): RatingListDao
    abstract fun newsDao(): NewsDao
    abstract fun adjudicatorDao(): AdjudicatorDao
    abstract fun competitionDao(): CompetitionDao
    abstract fun favoriteCoupleDao(): FavoriteCoupleDao
    abstract fun filterDao(): FilterDao
    abstract fun lastModifiedDao(): LastModifiedDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(context,
                AppDatabase::class.java, "pss.db")
                .build()
    }
}
