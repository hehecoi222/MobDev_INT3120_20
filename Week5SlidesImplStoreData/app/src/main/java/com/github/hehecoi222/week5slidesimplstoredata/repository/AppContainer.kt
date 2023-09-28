package com.github.hehecoi222.week5slidesimplstoredata.repository

import android.content.Context
import android.os.Environment
import com.github.hehecoi222.week5slidesimplstoredata.domain.repository.FileRepository
import com.github.hehecoi222.week5slidesimplstoredata.domain.repository.KeyValueStoreRepository
import com.github.hehecoi222.week5slidesimplstoredata.domain.repository.RoomAccessRepository
import com.github.hehecoi222.week5slidesimplstoredata.repository.datasources.DocumentSource
import com.github.hehecoi222.week5slidesimplstoredata.repository.datasources.PreferencesDataStore
import com.github.hehecoi222.week5slidesimplstoredata.repository.datasources.RoomAccessDatabase
import com.github.hehecoi222.week5slidesimplstoredata.repository.implementations.DocumentSourceRepositoryImpl
import com.github.hehecoi222.week5slidesimplstoredata.repository.implementations.KeyValueStoreRepositoryImpl

interface AppContainer {
    val keyValueStoreRepository: KeyValueStoreRepository
    val fileRepository: FileRepository
    val roomRepository: RoomAccessRepository
}

class DefaultAppContainer(context: Context) : AppContainer {
    private val dataStore = PreferencesDataStore(context)
    private val documentSource = DocumentSource(Environment.DIRECTORY_DOWNLOADS)
    private val dao = RoomAccessDatabase.getDatabase(context).simpleDAO()
    override val keyValueStoreRepository: KeyValueStoreRepository by lazy {
        KeyValueStoreRepositoryImpl(dataStore)
    }
    override val fileRepository: FileRepository by lazy {
        DocumentSourceRepositoryImpl(
            documentSource
        )
    }

    override val roomRepository: RoomAccessRepository by lazy {
        RoomAccessRepository(dao)
    }

}