package com.example.helloworld.fragment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.replace
import com.example.helloworld.R
import com.example.helloworld.fragment.news.NewsMainActivity

class FragmentMainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment_main)

        val btn_replace_fragment : Button = findViewById(R.id.btn_replace_fragment)
        btn_replace_fragment.setOnClickListener {
            replaceFragment(AnotherFragment())
        }
        replaceFragment(RightFragment())


        val btn_open_news : Button = findViewById(R.id.btn_open_news)
        btn_open_news.setOnClickListener {
            startActivity(Intent(this, NewsMainActivity::class.java))
        }

    }

    fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.rightLayout, fragment)
        transaction.addToBackStack(null)    // 添加到返回栈中
        transaction.commit()
    }

}

