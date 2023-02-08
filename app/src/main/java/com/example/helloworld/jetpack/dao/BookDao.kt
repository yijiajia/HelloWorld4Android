package com.example.helloworld.jetpack.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.helloworld.jetpack.model.Book

@Dao
interface BookDao {
    @Insert
    fun insertBook(book: Book) : Long

    @Query("select * from Book")
    fun getAllBooks() : List<Book>
}