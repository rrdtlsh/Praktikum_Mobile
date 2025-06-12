package com.example.modul5.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.modul5.data.local.dao.MakeupDao
import com.example.modul5.data.local.entity.MakeupEntity

@Database(entities = [MakeupEntity::class], version = 2, exportSchema = false)
abstract class MakeupDatabase : RoomDatabase() {
    abstract fun makeupDao(): MakeupDao

    companion object {
        @Volatile
        private var INSTANCE: MakeupDatabase? = null

        fun getDatabase(context: Context): MakeupDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MakeupDatabase::class.java,
                    "makeup_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}