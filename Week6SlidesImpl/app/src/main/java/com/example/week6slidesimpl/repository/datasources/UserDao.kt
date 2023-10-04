package com.example.week6slidesimpl.repository.datasources

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.week6slidesimpl.domain.entities.User

@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    fun getAll(): List<User>

    @Query("SELECT * FROM user WHERE id = :userId")
    fun getById(userId: Int): User

    @Query("SELECT * FROM user WHERE firstName = :firstName AND lastName = :lastName")
    fun getByName(firstName: String, lastName: String): List<User>

    @Insert
    fun insert(vararg user: User)

    @Delete
    fun delete(vararg user: User)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    fun update(user: User)
}