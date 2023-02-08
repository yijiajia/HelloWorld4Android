package com.example.helloworld.broadcast

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.helloworld.R

class BroadcastLoginCompleteActivity : BroadcastBaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_broadcast_login_complete)

        val btn_forceOffline : Button = findViewById(R.id.btn_forceOffline)
        btn_forceOffline.setOnClickListener {
            val intent = Intent("com.example.helloworld.FORCE_OFFLINE")
            intent.setPackage(packageName)
            sendBroadcast(intent)
        }
    }
}