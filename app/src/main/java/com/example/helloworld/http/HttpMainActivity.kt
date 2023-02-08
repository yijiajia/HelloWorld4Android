package com.example.helloworld.http

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.helloworld.R

class HttpMainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_http_main)

        val btn_openHttpConnection : Button = findViewById(R.id.btn_openHttpConnection)
        btn_openHttpConnection.setOnClickListener {
            startActivity(Intent(this, HttpRequestConnectionActivity::class.java))
        }

        val btn_openOkHttp : Button = findViewById(R.id.btn_openOkHttp)
        btn_openOkHttp.setOnClickListener {
            startActivity(Intent(this, OkHttpRequestActivity::class.java))
        }

        val btn_openRetrofit : Button = findViewById(R.id.btn_openRetrofit)
        btn_openRetrofit.setOnClickListener {
            startActivity(Intent(this, RetrofitActivity::class.java))
        }
    }
}