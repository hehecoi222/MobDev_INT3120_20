package com.github.hehecoi222.week5slidesimplstoredata.domain.repository

import androidx.datastore.preferences.core.Preferences

interface KeyValueStoreRepository {
    suspend fun contains(key: String): Boolean
    suspend fun getAll(): Map<Preferences.Key<*>, Any>?
    suspend fun get(key: String): String?
    suspend fun put(key: String, value: String)
    suspend fun delete(key: String)
}