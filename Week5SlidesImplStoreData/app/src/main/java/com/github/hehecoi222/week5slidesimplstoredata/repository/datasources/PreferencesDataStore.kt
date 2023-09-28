package com.github.hehecoi222.week5slidesimplstoredata.repository.datasources

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map

class PreferencesDataStore(private val context: Context) {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

    suspend fun read(key: String): String? {
        val result = context.dataStore.data.map {
            preferences ->
            preferences[stringPreferencesKey(key)]
        }
        return result.firstOrNull()
    }

    suspend fun readAll(): Map<Preferences.Key<*>, Any>? {
        val value = context.dataStore.data.map {
            preferences ->
            preferences.asMap()
        }
        return value.firstOrNull()
    }

    suspend fun write(key: String, value: String) {
        context.dataStore.edit {
            preferences ->
            preferences[stringPreferencesKey(key)] = value
        }
    }

    suspend fun delete(key: String) {
        context.dataStore.edit {
            preferences ->
            preferences.remove(stringPreferencesKey(key))
        }
    }
}