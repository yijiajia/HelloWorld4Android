package com.example.helloworld

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        // 打印发送过来的数据
        Log.d("SecondActivity",intent.getStringExtra("data1") + intent.getStringExtra("data2"))

        // 点击按钮返回数据
        val btnBack : Button = findViewById(R.id.btn_back)
        btnBack.setOnClickListener {
            backToFirstData()
        }
    }

    /**
     * 点击返回键来返回数据
     */
    override fun onBackPressed() {
        backToFirstData()
    }

    private fun backToFirstData() {
        val intent = Intent()
        intent.putExtra("data_return","come back to FirstActivity")
        setResult(RESULT_OK,intent)
        finish()    // 销毁当前页面
    }
}