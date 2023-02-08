package com.example.helloworld.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

open class BroadcastBaseActivity : AppCompatActivity() {

    lateinit var receiver : ForceOffLineReceiver

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        BroadcastActivityCollector.addActivity(this)
    }

    override fun onResume() {
        super.onResume()
        receiver = ForceOffLineReceiver()
        val intentFilter = IntentFilter()
        intentFilter.addAction("com.example.helloworld.FORCE_OFFLINE")
        registerReceiver(receiver,intentFilter)
    }

    override fun onPause() {
        super.onPause()
        unregisterReceiver(receiver)
    }

    override fun onDestroy() {
        super.onDestroy()
        BroadcastActivityCollector.removeActivity(this)
    }

    inner class ForceOffLineReceiver : BroadcastReceiver() {
        /**
         * 接收到强制下线消息时，弹出警告框
         */
        override fun onReceive(context: Context, intent: Intent?) {
            AlertDialog.Builder(context).apply {
                setTitle("Warning")
                setMessage("你被强制下线了，请重新登录")
                setCancelable(false)
                setPositiveButton("Ok") {_,_ ->
                    // 销毁所有Activity
                    BroadcastActivityCollector.finishAll()
                    val i = Intent(context,BroadcastLoginActivity::class.java)
                    context.startActivity(i)    // 重新启动登录页
                }
                show()
            }
        }

    }
}