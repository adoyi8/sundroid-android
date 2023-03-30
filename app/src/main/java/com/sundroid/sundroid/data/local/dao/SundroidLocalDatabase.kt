package com.sundroid.sundroid.data.local.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.sundroid.sundroid.data.local.dao.database_models.Job
import com.sundroid.sundroid.data.local.dao.database_models.RoomUserEntity
import com.sundroid.sundroid.data.local.dao.database_models.Shop
import com.sundroid.sundroid.data.local.dao.database_models.Staff


@Database(entities = [RoomUserEntity::class, Job::class, Shop::class, Staff::class], version = 12, exportSchema = false)
abstract class SundroidLocalDatabase : RoomDatabase() {
    abstract fun userDao(): SundroidDao
    companion object {
        @Volatile
        private var INSTANCE: SundroidLocalDatabase? = null

        fun getDatabase(context: Context): SundroidLocalDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SundroidLocalDatabase::class.java,
                    "sundroid_database"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                instance
            }
        }
    }
}
