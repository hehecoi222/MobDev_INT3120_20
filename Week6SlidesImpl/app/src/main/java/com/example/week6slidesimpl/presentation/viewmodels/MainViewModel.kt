package com.example.week6slidesimpl.presentation.viewmodels

import android.content.ContentResolver
import android.content.ContentValues
import android.database.Cursor
import android.database.SQLException
import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.week6slidesimpl.MainApplication
import com.example.week6slidesimpl.domain.entities.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

data class MainState(val allUser: List<List<String>>)

class MainViewModel(private val contentResolver: ContentResolver) : ViewModel() {
    var userInputFirstName by mutableStateOf("")
    var userInputLastName by mutableStateOf("")
    var selectedUpdate by mutableStateOf("")
    val columns = listOf("firstName", "lastName")
    var userInputUpdateValue by mutableStateOf("")
    private val _uiState = MutableStateFlow(MainState(emptyList()))
    val uiState = _uiState.asStateFlow()

    fun onLoadTab() {
        var cursor: Cursor? = null
        val listUser = MutableList(0) { _ -> List(0) { _ -> "" } }
        try {
            cursor = contentResolver.query(
                Uri.parse("content://com.example.week6slidesimpl/users"),
                null,
                null,
                null,
                null
            )

            if (cursor?.moveToFirst() == true) {
                val idIndex = cursor.getColumnIndex("id")
                val fNameIndex = cursor.getColumnIndex("firstName")
                val lNameIndex = cursor.getColumnIndex("lastName")
                if (idIndex < 0 || fNameIndex < 0 || lNameIndex < 0) {
                    throw SQLException("Not this table")
                }
                while (!cursor.isAfterLast) {
                    listUser.add(listOf(cursor.getString(idIndex), cursor.getString(fNameIndex), cursor.getString(lNameIndex)))
                    cursor.moveToNext()
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            cursor?.close()
            _uiState.value = MainState(listUser)
        }
    }

    fun onDelete(id: String) {
        try {
            val rows = contentResolver.delete(Uri.parse("content://com.example.week6slidesimpl/user/$id"), null, null)
            onLoadTab()
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
    }

    fun onUpdate(id: String) {
        try {
            val values = ContentValues()
            values.put("column", selectedUpdate)
            values.put("value", userInputUpdateValue)
            contentResolver.update(Uri.parse("content://com.example.week6slidesimpl/user/$id"), values, null, null)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun onSave() {
        try {
            val values = ContentValues()
            values.put("fname", userInputFirstName)
            values.put("lname", userInputLastName)
            contentResolver.insert(Uri.parse("content://com.example.week6slidesimpl/user"), values)
        } catch (t: Throwable) {
            t.printStackTrace()
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application =
                    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as MainApplication)
                MainViewModel(application.contentResolver)
            }
        }
    }
}