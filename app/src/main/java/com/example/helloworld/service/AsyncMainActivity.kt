package com.example.helloworld.service

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.helloworld.R

class AsyncMainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_async_main)

        val btn_open_ayncHandler : Button = findViewById(R.id.btn_open_ayncHandler)
        btn_open_ayncHandler.setOnClickListener {
            startActivity(Intent(this,AsyncMsgHandlerActivity::class.java))
        }

        val btn_open_service : Button = findViewById(R.id.btn_open_service)
        btn_open_service.setOnClickListener {
            startActivity(Intent(this,ServiceLifeCycleActivity::class.java))
        }

    }
}