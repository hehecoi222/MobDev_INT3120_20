package com.github.hehecoi222.week5slidesimplstoredata.domain.repository

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.github.hehecoi222.week5slidesimplstoredata.domain.entities.SimpleEntity

@Dao
interface SimpleDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: SimpleEntity)

    @Delete
    suspend fun delete(entity: SimpleEntity)

    @Query("SELECT * FROM simple_table")
    fun getAll(): LiveData<List<SimpleEntity>>

    @Update
    suspend fun update(entity: SimpleEntity)
}