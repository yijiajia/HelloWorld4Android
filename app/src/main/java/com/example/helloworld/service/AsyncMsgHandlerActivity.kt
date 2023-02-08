package com.example.helloworld.service

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.widget.Button
import android.widget.TextView
import com.example.helloworld.R
import kotlin.concurrent.thread

/**
 * 使用Handler对页面UI进行异步更新
 */
class AsyncMsgHandlerActivity : AppCompatActivity() {

    lateinit var textView : TextView
    val updateText = 1  // 修改文案的动作

    private val handler = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
           // 异步更新页面UI
            when(msg.what) {
                updateText -> textView.text = "HelloHandler"
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_async_msg_handler)

        textView = findViewById(R.id.text_msg)
        val btnUpdateUI : Button = findViewById(R.id.btn_update_ui)
        btnUpdateUI.setOnClickListener {
            // 使用子线程来发送异步消息
            thread {
                val msg = Message()
                msg.what = updateText
                handler.sendMessage(msg)
            }
        }
    }
}