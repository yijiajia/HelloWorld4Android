package com.example.helloworld

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class FourActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_four)
        Log.d("FourActivity",intent.getStringExtra("data1") + intent.getStringExtra("data2"))
    }

    companion object {
        /**
         * 提供外部启动当前Activity的方法，方便在启动时做一些特殊的逻辑，
         * 例如这里开放了两个参数才可以启动，而这两个参数是为了在两个activity中传递数据用的
         */
      fun actionStart(context: Context, data1 : String, data2 : String) {
          val intent = Intent(context,FourActivity::class.java)
          intent.putExtra("data1",data1)
          intent.putExtra("data2",data2)
          context.startActivity(intent)
      }
    }
}