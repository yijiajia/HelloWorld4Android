package com.example.helloworld.notice

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.app.NotificationCompat
import com.example.helloworld.R
import com.example.helloworld.uiBest.UIBestActivity

class NoticeMainActivity : AppCompatActivity() {

    /**
     * 渠道Id
     */
    private val channelId = "normal"

    /**
     * 渠道名称
     */
    private val channelName = "自定义通知"

    /**
     * 消息id
     */
    private val msgId = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notice_main)

        // 创建 manager 来管理通知
        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        // 创建渠道
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.FROYO) {    // Android8.0 以上才有下面的API
            val channel = NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_DEFAULT)
            manager.createNotificationChannel(channel)
        }

        val btn_create_notice : Button = findViewById(R.id.btn_create_notice)
        btn_create_notice.setOnClickListener {

            // 构建PendingIntent来响应通知的点击事件
            val pi = PendingIntent.getActivity(this,0,Intent(this,UIBestActivity::class.java),0)

            // 使用NotificationCompat.Builder 构建通知
            val notification = NotificationCompat.Builder(this,channelId)   // 必须传入已经创建好的渠道ID
//                .setContentText("这是通知内容")
                .setContentTitle("这是通知标题")
                .setSmallIcon(R.drawable.apple_pic)
                .setContentIntent(pi)       // 设置内容点击的Intent
                .setAutoCancel(true)        // 点击后自动关闭
                .setStyle(NotificationCompat.BigTextStyle().bigText("hahahahahha"))
                .build()
            /**
             * id ：参数id，需保证每个通知的id都是不同的
             */
            manager.notify(msgId,notification)
        }
    }
}