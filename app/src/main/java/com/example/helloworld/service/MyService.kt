package com.example.helloworld.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Binder
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import com.example.helloworld.R

class MyService : Service() {

    private val channelId = "Service"
    private val channelName = "前台Service"

    private val mBinder = DownLoadBinder()

    /**
     * 创建时执行
     */
    override fun onCreate() {
        super.onCreate()
        Log.d("MyService","MyService 创建了")

        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.FROYO) {
            val channel = NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_DEFAULT)
            manager.createNotificationChannel(channel)
        }

        // 发送通知
        val intent = Intent(this,ServiceLifeCycleActivity::class.java)
        val pi = PendingIntent.getActivity(this,0, intent, 0)
        val notification = NotificationCompat.Builder(this,channelId)
            .setContentTitle("这是通知标题")
            .setContentText("这是通知内容")
            .setSmallIcon(R.drawable.apple_pic)
            .setLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.apple_pic))
            .setContentIntent(pi)
            .build()
        // 启动前台Service
        startForeground(1, notification)
    }

    /**
     * 启动时执行
     */
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d("MyService","MyService 启动了")
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("MyService","MyService 停止了")
    }

    override fun onBind(intent: Intent?): IBinder {
        return mBinder
    }

    inner class DownLoadBinder : Binder() {
        fun startDownLoad() {
            Log.d("DownLoadBinder","startDownLoad executed")
        }

        fun getProcess() : Int {
            Log.d("DownLoadBinder","getProcess executed")
            return 0
        }
    }
}