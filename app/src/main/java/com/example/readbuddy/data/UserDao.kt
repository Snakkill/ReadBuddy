package com.example.readbuddy.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.readbuddy.model.User


@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: User)
    @Update
    suspend fun updateUser(user: User)


    @Delete
    suspend fun deleteUser(user: User)

    @Query("DELETE FROM reading_list")
    fun deleteAllUsers()

    @Query("SELECT * FROM reading_list ORDER BY title ASC")
    fun readAllData():LiveData<List<User>>
}