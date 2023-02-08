package com.example.helloworld

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.helloworld.broadcast.BroadcastReceiverActivity
import com.example.helloworld.broadcast.BroadcastTestActivity
import com.example.helloworld.contentProvider.ContentProviderMainActivity
import com.example.helloworld.fileSystem.FileSystemMainActivity
import com.example.helloworld.fragment.FragmentMainActivity
import com.example.helloworld.http.HttpMainActivity
import com.example.helloworld.jetpack.JetPackMainActivity
import com.example.helloworld.layout.FruitListViewActivity
import com.example.helloworld.layout.LayoutActivity
import com.example.helloworld.material.MaterialUIMainActivity
import com.example.helloworld.notice.NoticeMainActivity
import com.example.helloworld.persistent.PersistentActivity
import com.example.helloworld.service.AsyncMainActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val firstActivity : Button = findViewById(R.id.button_first)
        firstActivity.setOnClickListener {
            startActivity(Intent(this, FirstActivity::class.java))
        }

        val layoutActivity : Button = findViewById(R.id.button_layout)
        layoutActivity.setOnClickListener {
            startActivity(Intent(this,LayoutActivity::class.java))
        }

        val broadcastReceiver : Button= findViewById(R.id.button_broadcast)
        broadcastReceiver.setOnClickListener {
            startActivity(Intent(this, BroadcastReceiverActivity::class.java))
        }

        val persistentActivity : Button = findViewById(R.id.button_persistent)
        persistentActivity.setOnClickListener {
            startActivity(Intent(this,PersistentActivity::class.java))
        }

        val contentProviderMainActivity : Button = findViewById(R.id.button_content_provider)
        contentProviderMainActivity.setOnClickListener {
            startActivity(Intent(this,ContentProviderMainActivity::class.java))
        }

        val button_msg_notice : Button = findViewById(R.id.button_msg_notice)
        button_msg_notice.setOnClickListener {
            startActivity(Intent(this,NoticeMainActivity::class.java))
        }

        val button_open_file_system : Button = findViewById(R.id.button_open_file_system)
        button_open_file_system.setOnClickListener {
            startActivity(Intent(this,FileSystemMainActivity::class.java))
        }

        val button_open_aync : Button = findViewById(R.id.button_open_aync)
        button_open_aync.setOnClickListener {
            startActivity(Intent(this,AsyncMainActivity::class.java))
        }

        val button_open_http : Button = findViewById(R.id.button_open_http)
        button_open_http.setOnClickListener {
            startActivity(Intent(this, HttpMainActivity::class.java))
        }

        val button_open_fragment : Button = findViewById(R.id.button_open_fragment)
        button_open_fragment.setOnClickListener {
            startActivity(Intent(this,FragmentMainActivity::class.java))
        }

        val button_open_material : Button = findViewById(R.id.button_open_material)
        button_open_material.setOnClickListener {
            startActivity(Intent(this,MaterialUIMainActivity::class.java))
        }

        val button_open_jetpack : Button = findViewById(R.id.button_open_jetpack)
        button_open_jetpack.setOnClickListener {
            startActivity(Intent(this, JetPackMainActivity::class.java))
        }

    }
}