package com.example.week6slidesimpl.repository

import android.content.Context
import com.example.week6slidesimpl.domain.repositories.DatabaseAccessRepository
import com.example.week6slidesimpl.repository.datasources.AppRoomDatabase
import com.example.week6slidesimpl.repository.implementations.DatabaseAccessRepositoryImpl

interface AppContainer {
    val databaseAccessRepository: DatabaseAccessRepository
}

class DefaultAppContainer(context: Context): AppContainer {
    private val userDao = AppRoomDatabase.getDatabase(context).userDao()

    override val databaseAccessRepository: DatabaseAccessRepository by lazy {
        DatabaseAccessRepositoryImpl(userDao = userDao)
    }
}