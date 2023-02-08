package com.example.helloworld.contentProvider

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import android.util.Log
import com.example.helloworld.persistent.MySQLiteHelper

/**
 * 自定义contentProvider
 */
class MyContentProvider : ContentProvider() {

    private val bookDir = 0     // 访问Book所有数据
    private val bookItem = 1    // 访问Book单条数据
    private val categoryDir = 2
    private val categoryItem = 3

    private val authority = "com.example.helloworld.provider"
    private var dbHelper :MySQLiteHelper? = null

    private val uriMatcher by lazy {
        val matcher = UriMatcher(UriMatcher.NO_MATCH)
        matcher.addURI(authority,"book",bookDir)
        matcher.addURI(authority,"book/#",bookItem)
        matcher.addURI(authority,"category",categoryDir)
        matcher.addURI(authority,"category/#",categoryItem)
        matcher
    }

    override fun onCreate() = context?.let {
        dbHelper = MySQLiteHelper(it,"BookStroke.db",2)
        true
    }?: false


    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?) = dbHelper?.let {
        val db = it.writableDatabase
        val deleteRows = when(uriMatcher.match(uri)) {
            bookDir -> db.delete("Book",selection,selectionArgs)
            bookItem -> {
                val bookId = uri.pathSegments[1]
                db.delete("Book","id = ?", arrayOf(bookId))
            }
            categoryDir -> db.delete("Category",selection,selectionArgs)
            categoryItem -> {
                val categoryId = uri.pathSegments[1]
                db.delete("Category","id = ?", arrayOf(categoryId))
            }
            else -> 0
        }
        deleteRows
    } ?: 0

    override fun getType(uri: Uri) = when(uriMatcher.match(uri)) {
        bookDir -> "vnd.andorid.cursor.dir/vnd$authority.book"
        bookItem -> "vnd.andorid.cursor.item/vnd$authority.book"
        categoryDir -> "vnd.andorid.cursor.dir/vnd$authority.category"
        categoryItem -> "vnd.andorid.cursor.item/vnd$authority.category"
        else -> null
    }

    override fun insert(uri: Uri, values: ContentValues?) = dbHelper?.let {
        val db = it.writableDatabase
        val uriReturn = when(uriMatcher.match(uri)) {
            bookDir, bookItem -> {
                val newBookId = db.insert("Book",null,values)
                Uri.parse("content://$authority/book/$newBookId")
            }
            categoryDir, categoryItem -> {
                val newCategoryId = db.insert("Category",null,values)
                Uri.parse("content://$authority/category/$newCategoryId")
            }
            else -> null
        }
        uriReturn
    }



    override fun query(
        uri: Uri, projection: Array<String>?, selection: String?,
        selectionArgs: Array<String>?, sortOrder: String?
    ) = dbHelper?.let {
        val db = it.readableDatabase
        val cursor = when(uriMatcher.match(uri)) {
            bookDir -> {
                Log.d("","查询book的数据;uri=$uri")
                db.query("Book",projection,selection,selectionArgs,null,null,sortOrder)
            }
            bookItem -> {
                val bookId = uri.pathSegments[1]
                Log.d("","查询book的Item数据;id=$bookId")
                db.query("Book",projection,"id = ?", arrayOf(bookId),null,null,sortOrder)
            }
            categoryDir -> {
                Log.d("","查询Category的数据")
                db.query("Category",projection,selection,selectionArgs,null,null,sortOrder)
            }
            categoryItem -> {
                val categoryId = uri.pathSegments[1]
                Log.d("","查询Category Item的数据；id=$categoryId")
                db.query("Category",projection,"id = ?", arrayOf(categoryId),null,null,sortOrder)
            }
            else -> null
        }
        Log.d("","查询的数量有 ${cursor?.count ?: 0}")
        cursor
    }

    override fun update(
        uri: Uri, values: ContentValues?, selection: String?,
        selectionArgs: Array<String>?
    ) = dbHelper?.let {
        val db = it.writableDatabase
        val updateRows = when(uriMatcher.match(uri)) {
            bookDir -> db.update("Book",values,selection,selectionArgs)
            bookItem -> {
                val bookId = uri.pathSegments[1]
                db.update("Book",values,"id = ?", arrayOf(bookId))
            }
            categoryDir -> db.update("Category",values,selection,selectionArgs)
            categoryItem -> {
                val categoryId = uri.pathSegments[1]
                db.update("Category",values,"id = ?", arrayOf(categoryId))
            }
            else -> 0
        }
        updateRows
    } ?: 0
}