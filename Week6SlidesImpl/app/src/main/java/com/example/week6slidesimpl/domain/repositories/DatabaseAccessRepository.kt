package com.example.week6slidesimpl.domain.repositories

import android.content.ContentValues
import com.example.week6slidesimpl.domain.entities.User

interface DatabaseAccessRepository {
    fun getAll(): List<User>?
    fun getById(id: Int): User?

    fun deleteById(id: Int)
    fun insert(contentValues: ContentValues): User

    fun updateById(id: Int, column: String, value: String)
}