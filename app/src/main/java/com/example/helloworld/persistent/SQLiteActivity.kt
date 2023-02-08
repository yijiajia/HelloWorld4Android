package com.example.helloworld.persistent

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.example.helloworld.R

class SQLiteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sqlite)

        val dbHelper = MySQLiteHelper(this,"BookStroke.db",2)
        val db = dbHelper.writableDatabase

        val btn_createTable : Button = findViewById(R.id.btn_createTable)
        btn_createTable.setOnClickListener {
            dbHelper.writableDatabase
        }

        val btn_addData : Button = findViewById(R.id.btn_addData)
        btn_addData.setOnClickListener {
            db.beginTransaction()
            try {
                // 插入book
                val values1 = ContentValues().apply {
                    put("name","解忧杂货铺")
                    put("author","东野圭吾")
                    put("pages",200)
                    put("price",155.2)
                }
                db.insert("Book",null,values1)

                val values2 = ContentValues().apply {
                    put("name","老人与海")
                    put("author","里斯")
                    put("pages",22)
                    put("price",16.66)
                }
                val result = db.insert("Book",null,values2)

                // 插入category
                val values3 = ContentValues().apply {
                    put("category_name","科幻")
                    put("category_code",110)
                }
                db.insert("Category",null,values3)

                // 插入category
                val values4 = ContentValues().apply {
                    put("category_name","纪实")
                    put("category_code",120)
                }
                db.insert("Category",null,values4)

                db.setTransactionSuccessful()
                Toast.makeText(this,"添加数据成功；result=$result",Toast.LENGTH_SHORT).show()
            }finally {
                db.endTransaction()
            }

        }

        val btn_searchAll : Button = findViewById(R.id.btn_searchAll)
        btn_searchAll.setOnClickListener {
            // 查询book
            var cursor = db.query("Book",null,null,null,null,null,null)
            if(cursor.moveToFirst()) {
                do {
                    val name = cursor.getString(cursor.getColumnIndex("name"))
                    val author = cursor.getString(cursor.getColumnIndex("author"))
                    val pages = cursor.getInt(cursor.getColumnIndex("pages"))
                    val price = cursor.getDouble(cursor.getColumnIndex("price"))
                    Log.d("","search book db all data：name=$name,author=$author,pages=$pages,price=$price")

                }while (cursor.moveToNext())

                // 查询Category
                cursor = db.query("Category",null,null,null,null,null,null)
                if(cursor.moveToFirst()) {
                    do {
                        val categoryName = cursor.getString(cursor.getColumnIndex("category_name"))
                        val categoryCode = cursor.getInt(cursor.getColumnIndex("category_code"))

                        Log.d("","search category db all data：categoryName=$categoryName,categoryCode=$categoryCode")
                    }while (cursor.moveToNext())
                }
            }
            cursor.close()  // 关闭Close
        }


        val btn_update : Button = findViewById(R.id.btn_update)
        btn_update.setOnClickListener {
            val values = ContentValues()
            values.put("price",10)
            db.update("Book",values,"name = ?", arrayOf("老人与海"))
            Toast.makeText(this,"更新成功",Toast.LENGTH_SHORT).show()
        }

        val btn_del : Button = findViewById(R.id.btn_del)
        btn_del.setOnClickListener {
            db.delete("Book","pages > ?", arrayOf("20"))
        }

     /*   直接使用SQL
        db.execSQL("insert into Book (name,author,pages,price) values(?,?,?,?)", arrayOf("发条橙","zhangsan",88,50))
        db.execSQL("update Book set price = ? where name = ?", arrayOf(30,"发条橙"))
        db.execSQL("delete from Book where pages > ?", arrayOf(20))
          val cursor = db.rawQuery("select * from Book where name=?", arrayOf("发条橙"))
        */


        val btn_useTransaction : Button = findViewById(R.id.btn_useTransaction)
        btn_useTransaction.setOnClickListener {
            db.beginTransaction()       // 开启事务
            try {
                db.delete("Book","pages > ?", arrayOf("100"))
                if(true) {  // 假装异常了，那么数据删除不会成功
                    throw NullPointerException()
                }
                val values = ContentValues().apply {
                    put("name","我们为什么要睡觉觉")
                    put("author","who am i")
                    put("pages",100)
                    put("price",10)
                }
                db.insert("Book",null,values)
                db.setTransactionSuccessful()   // 事务执行结束，设置成功状态
            }catch (e : Exception) {
                e.printStackTrace()
            }finally {
                db.endTransaction() // 关闭事务
            }
        }



    }
}