package com.example.helloworld.http

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.example.helloworld.R
import com.example.helloworld.http.app.App
import com.example.helloworld.http.service.AppService
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retrofit)

        val btn_getApp : Button = findViewById(R.id.btn_getApp)
        btn_getApp.setOnClickListener {
            val retrofit = Retrofit.Builder()
                .baseUrl("http://192.168.31.222:8080/")// 本机ip
                .addConverterFactory(GsonConverterFactory.create()) // 格式化
                .build()
            val appService = retrofit.create<AppService>(AppService::class.java)
            appService.getAppData().enqueue(object : Callback<List<App>> {
                override fun onResponse(call: Call<List<App>>, response: Response<List<App>>) {
                   val list = response.body()
                    if(list != null) {
                        for (app in list) {
                            Log.d("","id is ${app.id},name is ${app.name}")
                        }
                    }
                }
                override fun onFailure(call: Call<List<App>>, t: Throwable) {
                    Log.d("","请求接口获取数据失败")
                    t.printStackTrace()
                }
            })

        }
    }
}