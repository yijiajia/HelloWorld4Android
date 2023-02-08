package com.example.helloworld.jetpack.dao

import androidx.room.*
import com.example.helloworld.jetpack.model.User

@Dao
interface UserDao {

    @Insert
    fun insertUser(user : User) : Long

    @Update
    fun updateUser(newUser: User)

    @Query("select * from User")
    fun loadAllUsers() : List<User>

    @Query("select * from User where age > :age")
    fun loadUserThanAge(age : Int) : List<User>

    @Delete
    fun delete(user: User)

    @Query("delete from User where lastName = :lastName")
    fun delete4LastName(lastName: String) : Int

}