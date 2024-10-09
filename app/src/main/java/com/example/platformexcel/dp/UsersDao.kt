package com.example.platformexcel.dp

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface UsersDao {

    @Upsert
    fun upsert(users: Platform)

    @Delete
    fun delete(users: Platform)

    @Query("Select * from users")
    fun getAll(): LiveData<List<Platform>>
    @Query("DELETE FROM users")
    fun deleteAll()
}