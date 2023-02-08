package com.example.helloworld.service

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.widget.Button
import com.example.helloworld.R

class ServiceLifeCycleActivity : AppCompatActivity() {

    lateinit var downLoadBinder : MyService.DownLoadBinder

    private val connection = object : ServiceConnection {

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            downLoadBinder = service as MyService.DownLoadBinder
            downLoadBinder.startDownLoad()
            downLoadBinder.getProcess()
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            // 断开连接时的操作
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service_life_cycle)

        val btn_startService : Button = findViewById(R.id.btn_startService)
        btn_startService.setOnClickListener {
            startService(Intent(this,MyService::class.java))
        }

        val btn_stopService : Button = findViewById(R.id.btn_stopService)
        btn_stopService.setOnClickListener {
            stopService(Intent(this,MyService::class.java))
        }

        val btn_bindService : Button = findViewById(R.id.btn_bindService)
        btn_bindService.setOnClickListener {
            val intent = Intent(this,MyService::class.java)
            bindService(intent, connection, Context.BIND_AUTO_CREATE)   // 绑定MyService，并自动创建
        }

        val btn_unBindService : Button = findViewById(R.id.btn_unBindService)
        btn_unBindService.setOnClickListener {
            unbindService(connection)   // 解绑MyService
        }

        val btn_startIntentService : Button = findViewById(R.id.btn_startIntentService)
        btn_startIntentService.setOnClickListener {
            Log.d("ServiceLifeCycleActivity","the current thread is ${Thread.currentThread().name}")
            startService(Intent(this,MyIntentService::class.java))
        }
    }
}