package com.example.helloworld.fileSystem

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.VideoView
import com.example.helloworld.R

/**
 * 视频播放器
 */
class VideoViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_view)
        val uri = Uri.parse("android.resource://$packageName/${R.raw.video}")
        val videoView : VideoView = findViewById(R.id.videoView)
        videoView.setVideoURI(uri)

        val btn_video_start : Button = findViewById(R.id.btn_video_start)
        btn_video_start.setOnClickListener {
            if(!videoView.isPlaying) {
                videoView.start()
            }
        }

        val btn_video_pause : Button = findViewById(R.id.btn_video_pause)
        btn_video_pause.setOnClickListener {
            if(videoView.isPlaying) {
                videoView.pause()
            }
        }

        val btn_video_stop : Button = findViewById(R.id.btn_video_stop)
        btn_video_stop.setOnClickListener {
            if(videoView.isPlaying) {
                videoView.resume()  // 重新播放
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        val videoView : VideoView = findViewById(R.id.videoView)
        videoView.suspend() // 释放资源
    }
}