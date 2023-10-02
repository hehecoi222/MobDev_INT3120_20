package com.example.week6slidesimpl.provider

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.database.MatrixCursor
import android.net.Uri
import androidx.room.Room
import com.example.week6slidesimpl.domain.repositories.DatabaseAccessRepository
import com.example.week6slidesimpl.repository.datasources.AppRoomDatabase
import com.example.week6slidesimpl.repository.datasources.UserDao


private val uriMatcher = UriMatcher(UriMatcher.NO_MATCH).apply {
    addURI("com.example.week6slidesimpl", "user/#", 1)
    addURI("com.example.week6slidesimpl", "user", 2)
}
class UserProvider(private val databaseAccessRepository: DatabaseAccessRepository): ContentProvider() {
    override fun onCreate(): Boolean {
        return true
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {
        when (uriMatcher.match(uri)) {
            1 -> {
                val user = uri.lastPathSegment?.let { databaseAccessRepository.getById(it.toInt()) }
                return MatrixCursor(arrayOf(user.toString()))
            }
            2 -> {
                val users = databaseAccessRepository.getAll()
                if (users != null) {
                    return MatrixCursor(users.map { it.toString() }.toTypedArray())
                }
            }
            else -> { }
        }
        return null
    }

    override fun getType(uri: Uri): String? {
        TODO("Not yet implemented")
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        TODO("Not yet implemented")
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        TODO("Not yet implemented")
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
        TODO("Not yet implemented")
    }
}