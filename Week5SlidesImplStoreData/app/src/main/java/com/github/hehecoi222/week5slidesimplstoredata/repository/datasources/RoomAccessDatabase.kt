package com.github.hehecoi222.week5slidesimplstoredata.repository.datasources

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.github.hehecoi222.week5slidesimplstoredata.domain.entities.SimpleEntity
import com.github.hehecoi222.week5slidesimplstoredata.domain.repository.SimpleDAO

@Database(entities = [SimpleEntity::class], version = 1)
abstract class RoomAccessDatabase : RoomDatabase() {
    abstract fun simpleDAO(): SimpleDAO

    companion object {
        @Volatile
        private var INSTANCE: RoomAccessDatabase? = null

        fun getDatabase(context: Context): RoomAccessDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RoomAccessDatabase::class.java,
                    "simple_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}