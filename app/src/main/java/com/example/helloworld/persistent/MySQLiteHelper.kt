package com.example.helloworld.persistent

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

class MySQLiteHelper(
    val context: Context?,
    val name: String?,
    val version: Int
) : SQLiteOpenHelper(context, name,null, version) {

    private val createBookTable = "create table Book (id integer primary key autoincrement, author text,price real,pages integer,name text)"
    private val createCategoryTable = "create table Category (id integer primary key autoincrement, category_name text,category_code integer)"

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(createBookTable)
        db.execSQL(createCategoryTable)
//        Toast.makeText(context,"Create Book Table Successed",Toast.LENGTH_SHORT).show()
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
       /* if(oldVersion <=3) {
            db.execSQL(createCategoryTable)
        }*/
    }
}