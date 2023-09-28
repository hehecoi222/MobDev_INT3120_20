package com.github.hehecoi222.week5slidesimplstoredata.domain.repository

interface FileRepository {
    suspend fun saveData(key: String, value: String)
    suspend fun loadAll(): Map<String, String>?
    suspend fun remove(key: String)
    fun changeFileName(fileName: String)
}