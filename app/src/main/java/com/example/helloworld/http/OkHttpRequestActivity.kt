package com.example.helloworld.http

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.helloworld.R
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import kotlin.concurrent.thread

class OkHttpRequestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ok_http_request)

        val btn_update_ui_text_2 : Button = findViewById(R.id.btn_update_ui_text_2)
        btn_update_ui_text_2.setOnClickListener {
            sendRequestWithOkHttp()
        }
    }

    fun sendRequestWithOkHttp() {
        thread {
            try {
                val httpClient = OkHttpClient()
                val request = Request.Builder()
                    .url("https://www.baidu.com")
                    .build()
                val response = httpClient.newCall(request).execute()
                val data = response.body()?.string()
                if (data != null) {
                    showResponse(data)
                }
            }catch (e : Exception) {
                e.printStackTrace()
            } finally {
            }
        }
    }

    fun showResponse(response: String) {
        runOnUiThread { // 利用Handler异步更新UI的方法
            val responseText : TextView = findViewById(R.id.text_response_2)
            responseText.text = response
        }
    }
}