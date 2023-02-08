package com.example.helloworld.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.helloworld.R

class BroadcastTestActivity : AppCompatActivity() {

    lateinit var timeReceiver : TimerBroadCastReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_broadcast_test)

        val intentFilter = IntentFilter()
        intentFilter.addAction("android.intent.action.TIME_TICK")
        timeReceiver = TimerBroadCastReceiver()
        registerReceiver(timeReceiver, intentFilter)
    }

    /**
     * 动态注册的广播需要销毁掉
     */
    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(timeReceiver)
    }

    inner class TimerBroadCastReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
           Toast.makeText(context,"Time has changed",Toast.LENGTH_SHORT).show()
        }
    }

}