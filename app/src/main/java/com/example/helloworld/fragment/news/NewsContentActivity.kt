package com.example.helloworld.fragment.news

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.helloworld.R

class NewsContentActivity : AppCompatActivity() {

    companion object {
        /**
         * 提供给外部启动当前Activity的方法，启动时带上新闻参数（实际上是带id，然后查db
         */
        fun actionStart(context: Context, title: String, content: String) {
            val intent = Intent(context, NewsContentActivity::class.java).apply {
                putExtra("news_title",title)
                putExtra("news_content",content)
            }
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_content)
        val title = intent.getStringExtra("news_title")
        val content = intent.getStringExtra("news_content")
        if(title != null && content != null) {
            val fragment = supportFragmentManager.findFragmentById(R.id.newsContentFrag) as NewsContentFrag
            fragment.refresh(title, content)    // 刷新Fragment的界面
        }
    }
}