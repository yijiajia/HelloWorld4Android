package com.example.helloworld.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class MyBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        Toast.makeText(context,"接收自定义的广播信息",Toast.LENGTH_SHORT).show()
        abortBroadcast()    // 丢弃广播，表示截断
    }
}