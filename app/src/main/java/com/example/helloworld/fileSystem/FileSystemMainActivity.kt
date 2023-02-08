package com.example.helloworld.fileSystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.helloworld.R

class FileSystemMainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_file_system_main)

        val openCameraActivity : Button = findViewById(R.id.openCameraActivity)
        openCameraActivity.setOnClickListener {
            startActivity(Intent(this,CameraAlbumActivity::class.java))
        }

        val openMediaActivity : Button = findViewById(R.id.openMediaActivity)
        openMediaActivity.setOnClickListener {
            startActivity(Intent(this,MediaPlayerActivity::class.java))
        }

        val openVideoActivity : Button = findViewById(R.id.openVideoActivity)
        openVideoActivity.setOnClickListener {
            startActivity(Intent(this,VideoViewActivity::class.java))
        }

    }
}