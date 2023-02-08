package com.example.helloworld.jetpack

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.work.BackoffPolicy
import androidx.work.OneTimeWorkRequest
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.example.helloworld.R
import java.util.concurrent.TimeUnit

class JetPackMainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jet_pack_main)

        val viewModelCounterActivity : Button = findViewById(R.id.open_view_model_counter)
        viewModelCounterActivity.setOnClickListener{
            startActivity(Intent(this, ViewModelCounterActivity::class.java))
        }
        val roomMainActivity : Button = findViewById(R.id.open_room)
        roomMainActivity.setOnClickListener {
            startActivity(Intent(this, RoomMainActivity::class.java))
        }

        // workManager
        val open_doWork : Button = findViewById(R.id.open_doWork)
        open_doWork.setOnClickListener {
            val request = OneTimeWorkRequest.Builder(SimpleWorker::class.java)
                .setInitialDelay(5,TimeUnit.MINUTES)
                .setBackoffCriteria(BackoffPolicy.EXPONENTIAL,10,TimeUnit.SECONDS)
                .addTag("simple")   // 添加标签
                .build()  // 构建任务
            WorkManager.getInstance(this).enqueue(request)
        }
    }
}