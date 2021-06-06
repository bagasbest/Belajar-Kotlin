package com.bagasbest.beoskop21.model.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.bagasbest.beoskop21.model.source.local.entity.MovieEntity
import com.bagasbest.beoskop21.model.source.local.entity.SeriesEntity

@Database(entities = [MovieEntity::class, SeriesEntity::class], version = 1, exportSchema = false)
abstract class BeOskopDatabase : RoomDatabase() {

    abstract fun beOskopDao(): BeOskopDao

    companion object {
        @Volatile
        private var INSTANCE: BeOskopDatabase? = null

        @JvmStatic
        fun getInstance(context: Context): BeOskopDatabase =
            INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    BeOskopDatabase::class.java,
                    "BeOskop.db",
                ).build().apply {
                    INSTANCE = this
                }
            }
    }
}