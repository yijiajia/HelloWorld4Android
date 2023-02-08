package com.example.helloworld.persistent

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.example.helloworld.R
import com.example.helloworld.broadcast.BroadcastLoginActivity

class SharedPreferencesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shared_preferences)
        // 点击保存数据
        val saveButton : Button = findViewById(R.id.btn_save_share)
        saveButton.setOnClickListener {
            val edit : SharedPreferences.Editor = getSharedPreferences("data", MODE_PRIVATE).edit()
            edit.putString("name","zhangsan")
            edit.putInt("age",18)
            edit.putBoolean("isMan",true)
            edit.apply()
            Toast.makeText(this,"保存数据成功",Toast.LENGTH_SHORT).show()
        }

        val loadButton : Button = findViewById(R.id.btn_load_share)
        loadButton.setOnClickListener {
            val prefs = getSharedPreferences("data", MODE_PRIVATE)
            val name = prefs.getString("name","")
            val age = prefs.getInt("age",0)
            val isMan = prefs.getBoolean("isMan",false)
            Log.d("","name=$name,age=$age,isMan=$isMan")
            Toast.makeText(this,"加载数据完成；name=$name,age=$age,isMan=$isMan",Toast.LENGTH_SHORT).show()
        }

        val loginButton : Button = findViewById(R.id.btn_login_share)
        loginButton.setOnClickListener {
            startActivity(Intent(this,BroadcastLoginActivity::class.java))
        }


    }
}