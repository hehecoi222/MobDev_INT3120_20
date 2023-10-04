package com.github.hehecoi222.week5slidesimplstoredata.domain.state

import androidx.datastore.preferences.core.Preferences
import com.github.hehecoi222.week5slidesimplstoredata.domain.entities.SimpleEntity

data class MainState(
    val allKeyDS: Map<Preferences.Key<*>, Any>?,
    val allKeyFile: Map<String, String>?,
    val allKeyRoom: List<SimpleEntity>?,
    val allUser: List<List<String>>?
) {
}