package com.example.helloworld.http.service

import com.example.helloworld.http.app.App
import retrofit2.Call
import retrofit2.http.GET

interface AppService {

    @GET("getApp")
    fun getAppData() : Call<List<App>>
}