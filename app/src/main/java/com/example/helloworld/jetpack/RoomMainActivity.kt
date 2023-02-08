package com.example.helloworld.jetpack

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.example.helloworld.R
import com.example.helloworld.http.app.App
import com.example.helloworld.jetpack.dao.AppDatabase
import com.example.helloworld.jetpack.model.Book
import com.example.helloworld.jetpack.model.User
import kotlin.concurrent.thread

class RoomMainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room_main)
        val addUser : Button = findViewById(R.id.btn_addUser)
        val updateUser : Button = findViewById(R.id.btn_updateUser)
        val loadAllUser : Button = findViewById(R.id.btn_loadAllUser)
        val getUserByAge : Button = findViewById(R.id.btn_getUserByAge)
        val deleteUser : Button = findViewById(R.id.btn_deleteUser)
        val deleteUserByLastName : Button = findViewById(R.id.btn_deleteUserByLastName)
        val addBook : Button = findViewById(R.id.btn_addBook)
        val getAllBooks : Button = findViewById(R.id.btn_getAllBooks)

        val userDao = AppDatabase.getDatabase(this).getUserDao()
        val bookDao = AppDatabase.getDatabase(this).getBookDao()

        val user1 = User("Tom","Brady",40)
        val user2 = User("Jerry","Jet",19)
        val user3 = User("san","Zhang",10)

        val book1 = Book("卡叶琳娜大帝",1000)

        addUser.setOnClickListener {
            thread {
                user1.id = userDao.insertUser(user1)
                user2.id = userDao.insertUser(user2)
                user3.id = userDao.insertUser(user3)
                Log.d("","添加用户数据成功")
            }
        }

        updateUser.setOnClickListener {
            thread {
                user1.age = 30
                userDao.updateUser(user1)
            }
        }

        deleteUser.setOnClickListener {
            thread {
                userDao.delete(user2)
            }
        }

        deleteUserByLastName.setOnClickListener {
            thread {
                userDao.delete4LastName(user1.lastName)
            }
        }

        loadAllUser.setOnClickListener {
            thread {
                val userList = userDao.loadAllUsers()
                for (user in userList) {
                    Log.d("",user.toString())
                }
            }
        }

        getUserByAge.setOnClickListener {
            thread {
                thread {
                    val userList = userDao.loadUserThanAge(20)
                    for (user in userList) {
                        Log.d("",user.toString())
                    }
                }
            }
        }


        addBook.setOnClickListener {
            thread {
                book1.id = bookDao.insertBook(book1)
            }
//            Toast.makeText(this,"书籍添加成功；book=$book1",Toast.LENGTH_SHORT).show()
        }

        getAllBooks.setOnClickListener {
            thread {
                val bookList = bookDao.getAllBooks()
                for(book in bookList) {
                    Log.d("","获取到的书籍有 $book")
                }
            }
        }


    }
}