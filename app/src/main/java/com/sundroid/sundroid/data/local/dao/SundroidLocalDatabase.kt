package com.sundroid.sundroid.data.local.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.sundroid.sundroid.models.RoomUserEntity













@Database(entities = [RoomUserEntity::class], version = 2, exportSchema = false)
abstract class SundroidLocalDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

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
