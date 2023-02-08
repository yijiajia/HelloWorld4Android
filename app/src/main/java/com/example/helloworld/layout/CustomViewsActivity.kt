package com.example.helloworld.layout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.helloworld.R

class CustomViewsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_views)
        supportActionBar?.hide()
    }
}