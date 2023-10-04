package com.example.week6slidesimpl.repository.implementations

import android.content.ContentValues
import com.example.week6slidesimpl.domain.entities.User
import com.example.week6slidesimpl.domain.repositories.DatabaseAccessRepository
import com.example.week6slidesimpl.repository.datasources.UserDao

class DatabaseAccessRepositoryImpl(private val userDao: UserDao): DatabaseAccessRepository{
    override fun getAll(): List<User> {
        return userDao.getAll()
    }

    override fun getById(id: Int): User {
        return userDao.getById(id)
    }

    override fun insert(contentValues: ContentValues): User {
        val user = User(firstName = contentValues.getAsString("fname"), lastName = contentValues.getAsString("lname"))
        userDao.insert(user)
        return user
    }

    override fun deleteById(id: Int) {
        val user = getById(id)
        userDao.delete(user)
    }

    override fun updateById(id: Int, column: String, value: String) {
        var user: User? = null
        when (column) {
            "firstName" -> user = getById(id).copy(firstName = value)
            "lastName" -> user = getById(id).copy(lastName = value)
        }
        if (user != null) {
            userDao.update(user)
        }
    }
}