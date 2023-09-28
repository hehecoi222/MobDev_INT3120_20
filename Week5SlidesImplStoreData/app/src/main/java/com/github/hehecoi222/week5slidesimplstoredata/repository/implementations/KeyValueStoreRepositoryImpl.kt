package com.github.hehecoi222.week5slidesimplstoredata.repository.implementations

import androidx.datastore.preferences.core.Preferences
import com.github.hehecoi222.week5slidesimplstoredata.domain.repository.KeyValueStoreRepository
import com.github.hehecoi222.week5slidesimplstoredata.repository.datasources.PreferencesDataStore

class KeyValueStoreRepositoryImpl(private val dataStore: PreferencesDataStore) :
    KeyValueStoreRepository {
    override suspend fun contains(key: String): Boolean {
        val result = dataStore.read(key)
        return result != null
    }

    override suspend fun getAll(): Map<Preferences.Key<*>, Any>? {
        return dataStore.readAll()
    }

    override suspend fun get(key: String): String? {
        val result = dataStore.read(key)
        return result
    }

    override suspend fun put(key: String, value: String) {
        try {
            dataStore.write(key, value)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override suspend fun delete(key: String) {
        try {
            dataStore.delete(key)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}