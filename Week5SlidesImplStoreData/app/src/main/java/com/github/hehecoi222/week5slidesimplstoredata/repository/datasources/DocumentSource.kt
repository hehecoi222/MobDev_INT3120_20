package com.github.hehecoi222.week5slidesimplstoredata.repository.datasources

import android.os.Environment
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

class DocumentSource(private val path: String, fileName: String = "default.txt") {
    private var file = File(Environment.getExternalStoragePublicDirectory(path), fileName)

    suspend fun saveData(key: String, value: String) {
        var fileOutputStream: FileOutputStream? = null
        try {
            fileOutputStream = withContext(Dispatchers.IO) {
                FileOutputStream(file, true)
            }
            withContext(Dispatchers.IO) {
                fileOutputStream.write("$key:$value\n".toByteArray())
            }
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            withContext(Dispatchers.IO) {
                fileOutputStream?.close()
            }
        }
    }

    fun setFile(fileName: String) {
        file = File(Environment.getExternalStoragePublicDirectory(path), fileName)
    }

    suspend fun loadAll(): Map<String, String> {
        Log.d("DocumentSource", "loadAll: ")
        var fileInputStream: FileInputStream? = null
        try {
            fileInputStream = file.inputStream()
            val allData = fileInputStream.bufferedReader().use { it.readText() }
            Log.d("DocumentSource", "loadAll: $allData")
            val allDataMap = allData.split("\n").associate {
                if (it == "") return@associate "" to ""
                val key = it.split(":")[0]
                val value = it.split(":")[1]
                key to value
            }
            withContext(Dispatchers.IO) {
                fileInputStream.close()
            }
            return allDataMap
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            withContext(Dispatchers.IO) {
                fileInputStream?.close()
            }
        }
        return mapOf()
    }

    suspend fun remove(key: String) {
        val allData = loadAll()
        val newData = allData.filter {
            it.key != key
        }
        val fileOutputStream = withContext(Dispatchers.IO) {
            FileOutputStream(file, false)
        }
        withContext(Dispatchers.IO) {
            fileOutputStream.write("".toByteArray())
        }
        newData.forEach {
            saveData(it.key, it.value)
        }
        withContext(Dispatchers.IO) {
            fileOutputStream.close()
        }
    }
}