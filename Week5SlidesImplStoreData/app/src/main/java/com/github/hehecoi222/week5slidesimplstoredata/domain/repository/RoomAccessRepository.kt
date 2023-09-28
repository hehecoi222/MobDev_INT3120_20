package com.github.hehecoi222.week5slidesimplstoredata.domain.repository

import androidx.lifecycle.LiveData
import com.github.hehecoi222.week5slidesimplstoredata.domain.entities.SimpleEntity

class RoomAccessRepository(private val simpleDAO: SimpleDAO){
    val allKeys: LiveData<List<SimpleEntity>> = simpleDAO.getAll()

    suspend fun insert(entity: SimpleEntity){
        simpleDAO.insert(entity)
    }

    suspend fun delete(entity: SimpleEntity){
        simpleDAO.delete(entity)
    }

    suspend fun update(entity: SimpleEntity){
        simpleDAO.update(entity)
    }
}