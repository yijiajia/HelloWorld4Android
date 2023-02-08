package com.example.helloworld.http

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.helloworld.R
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.CacheResponse
import java.net.HttpURLConnection
import java.net.URL
import kotlin.concurrent.thread

class HttpRequestConnectionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_http_request_connection)

        val btn_update_ui_text_1 : Button = findViewById(R.id.btn_update_ui_text_1)
        btn_update_ui_text_1.setOnClickListener {
            sendRequestWithHttpURLConnection()
        }
    }

    fun sendRequestWithHttpURLConnection() {
        thread {
            var connection : HttpURLConnection? = null
            try {
                val response = StringBuilder()
                val url = URL("https://cn.bing.com/")
                connection = url.openConnection() as HttpURLConnection
                connection.connectTimeout = 8000
                connection.readTimeout = 8000
                val input = connection.inputStream

                val reader = BufferedReader(InputStreamReader(input))
                reader.use {
                    reader.forEachLine {
                        response.append(it)
                    }
                }
                showResponse(response.toString())
            }catch (e : Exception) {
                e.printStackTrace()
            } finally {
                connection?.disconnect()
            }
        }
    }

    fun showResponse(response: String) {
        runOnUiThread { // 利用Handler异步更新UI的方法
            val responseText : TextView = findViewById(R.id.text_response_1)
            responseText.text = response
        }
    }
}