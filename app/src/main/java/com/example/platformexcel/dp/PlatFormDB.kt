package com.example.platformexcel.dp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
@Database(entities = [Platform::class], version = 1, exportSchema = false)
abstract class PlatFormDB : RoomDatabase() {

    abstract fun dao(): UsersDao

    companion object {
        @Volatile
        private var INSTANCE: PlatFormDB? = null
        fun getDatabase(context: Context): PlatFormDB {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(context, PlatFormDB::class.java, "users")
                    .allowMainThreadQueries().build()
                INSTANCE = instance
                instance
            }
        }
    }
}