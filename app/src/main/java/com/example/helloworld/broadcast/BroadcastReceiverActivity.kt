package com.example.helloworld.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.helloworld.R

class BroadcastReceiverActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_broadcast_receiver)

        val broadcastTestActivity : Button = findViewById(R.id.btn_broadcast_test)
        broadcastTestActivity.setOnClickListener {
            startActivity(Intent(this,BroadcastTestActivity::class.java))
        }

        val broadcastSend : Button = findViewById(R.id.btn_broadcast_send)
        broadcastSend.setOnClickListener {
            val intent = Intent("com.example.helloworld.MY_BROADCAST")
            // 自定义广播默认是隐式广播，而隐式广播是无法被静态注册的broadcast接收的，
            // setPackage表示这条广播是发送给哪个程序的，从而让它变为一个显示广播
            intent.setPackage(packageName)
//            sendBroadcast(intent) // 发送标准的广播，由priority决定优先级
            sendOrderedBroadcast(intent,null) // 发送有序的广播，可被截断
        }

        val broadcastLogin : Button = findViewById(R.id.btn_broadcast_login)
        broadcastLogin.setOnClickListener {
            startActivity(Intent(this,BroadcastLoginActivity::class.java))
        }
    }

}