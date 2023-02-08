package com.example.helloworld.persistent

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.helloworld.R

class PersistentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_persistent)

        val btn_file_persistent_test : Button = findViewById(R.id.btn_file_persistent_test)
        btn_file_persistent_test.setOnClickListener {
            startActivity(Intent(this,FilePersistentActivity::class.java))
        }

        val btn_shared_persistent_test : Button = findViewById(R.id.btn_shared_persistent_test)
        btn_shared_persistent_test.setOnClickListener {
            startActivity(Intent(this,SharedPreferencesActivity::class.java))
        }

        val btn_sql_lite_test : Button = findViewById(R.id.btn_sql_lite_test)
        btn_sql_lite_test.setOnClickListener {
            startActivity(Intent(this,SQLiteActivity::class.java))
        }


    }
}