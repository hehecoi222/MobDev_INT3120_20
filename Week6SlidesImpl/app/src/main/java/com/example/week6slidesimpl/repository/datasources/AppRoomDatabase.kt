package com.example.week6slidesimpl.repository.datasources

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.week6slidesimpl.domain.entities.User

@Database(
    entities = [User::class],
    version = 1
)
abstract class AppRoomDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var INSTANCE: AppRoomDatabase? = null

        fun getDatabase(context: Context): AppRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppRoomDatabase::class.java,
                    "simple_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}