package com.example.helloworld.fileSystem

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.helloworld.R

/**
 * 播放音频
 */
class MediaPlayerActivity : AppCompatActivity() {

    private val mediaPlayer = MediaPlayer()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_media_player)

        initMediaPlayer()

        val btn_player_start : Button = findViewById(R.id.btn_player_start)
        btn_player_start.setOnClickListener {
            if(!mediaPlayer.isPlaying) {
                mediaPlayer.start()
            }
        }

        val btn_player_pause : Button = findViewById(R.id.btn_player_pause)
        btn_player_pause.setOnClickListener {
            if(mediaPlayer.isPlaying) {
                mediaPlayer.pause()
            }
        }

        val btn_player_reset : Button = findViewById(R.id.btn_player_reset)
        btn_player_reset.setOnClickListener {
            if(mediaPlayer.isPlaying) {
                mediaPlayer.reset()
                initMediaPlayer()
            }
        }

    }


    /**
     * 初始化音乐播放器
     */
    private fun initMediaPlayer() {
        val manager = assets
//        val fd = manager.openFd("santi.wma") // 播放wma格式会报错
        val fd = manager.openFd("mianshi.mp3")
        mediaPlayer.setDataSource(fd.fileDescriptor, fd.startOffset, fd.length)
        mediaPlayer.prepare()
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.stop()
        mediaPlayer.release()
    }
}