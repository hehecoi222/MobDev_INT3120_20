package com.example.week6slidesimpl.repository.implementations

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

}