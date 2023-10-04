package com.example.week6slidesimpl

import android.content.ContentProvider
import android.content.ContentUris
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.database.MatrixCursor
import android.database.SQLException
import android.net.Uri

private val uriMatcher = UriMatcher(UriMatcher.NO_MATCH).apply {
    addURI("com.example.week6slidesimpl", "user/#", 1)
    addURI("com.example.week6slidesimpl", "users", 2)
    addURI("com.example.week6slidesimpl", "user", 3)
}
class UserProvider(): ContentProvider() {
    private var databaseAccessRepository = (context?.applicationContext as MainApplication?)?.container?.databaseAccessRepository
    override fun onCreate(): Boolean {
        return true
    }

    private fun loadDB() {
        if (databaseAccessRepository == null) databaseAccessRepository = (context?.applicationContext as MainApplication?)?.container?.databaseAccessRepository
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {
        loadDB()
        when (uriMatcher.match(uri)) {
            1 -> {
                val user = uri.lastPathSegment?.let { databaseAccessRepository?.getById(it.toInt()) }
                val cursor = MatrixCursor(arrayOf("id", "firstName", "lastName"))
                if (user != null) {
                    cursor.addRow(arrayOf(user.id, user.firstName, user.lastName))
                }
                return cursor
            }
            2 -> {
                val cursor = MatrixCursor(arrayOf("id", "firstName", "lastName"))
                databaseAccessRepository?.getAll()?.forEach {
                    cursor.addRow(arrayOf(it.id, it.firstName, it.lastName))
                }
                return cursor
            }
            else -> { }
        }
        return null
    }

    override fun getType(uri: Uri): String? {
        TODO("Not yet implemented")
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        loadDB()
        if (uriMatcher.match(uri) == 3) {
            if (values != null) {
                val user = databaseAccessRepository?.insert(values)
                if (user != null) {
                    return ContentUris.withAppendedId(uri, user.id.toLong())
                }
            }
        }
        throw SQLException("Failed to instert")
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        loadDB()
        if (uriMatcher.match(uri) == 1) {
            return try {
                uri.lastPathSegment?.let { databaseAccessRepository?.deleteById(it.toInt()) }
                1
            } catch (t: Throwable) {
                t.printStackTrace()
                0
            }
        }
        return 0
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
        loadDB()
        if (uriMatcher.match(uri) == 1) {
            return try {
                if (values != null) {
                    uri.lastPathSegment?.let { databaseAccessRepository?.updateById(it.toInt(), values.getAsString("column"), values.getAsString("value")) }
                }
                1
            } catch (t: Throwable) {
                t.printStackTrace()
                0
            }
        }
        return 0
    }

    companion object {
        const val PROVIDER_NAME = "com.example.week6slidesimpl"
        const val URL = "content://" + PROVIDER_NAME + "/"
    }
}