package com.example.week6slidesimpl.domain.repositories

import com.example.week6slidesimpl.domain.entities.User

interface DatabaseAccessRepository {
    fun getAll(): List<User>?
    fun getById(id: Int): User?
}