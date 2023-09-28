package com.github.hehecoi222.week5slidesimplstoredata.repository.implementations

import com.github.hehecoi222.week5slidesimplstoredata.domain.repository.FileRepository
import com.github.hehecoi222.week5slidesimplstoredata.repository.datasources.DocumentSource

class DocumentSourceRepositoryImpl(private val documentSource: DocumentSource) : FileRepository {
    override suspend fun saveData(key: String, value: String) {
        documentSource.saveData(key, value)
    }

    override suspend fun loadAll(): Map<String, String>? {
        return documentSource.loadAll()
    }

    override suspend fun remove(key: String) {
        documentSource.remove(key)
    }

    override fun changeFileName(fileName: String) {
        documentSource.setFile(fileName)
    }

}