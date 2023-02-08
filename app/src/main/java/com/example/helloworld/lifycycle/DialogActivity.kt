package com.example.helloworld.lifycycle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.helloworld.R

class DialogActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dialog)
    }
}