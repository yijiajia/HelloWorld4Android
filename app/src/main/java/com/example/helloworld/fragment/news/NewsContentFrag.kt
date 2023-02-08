package com.example.helloworld.fragment.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.helloworld.R

class NewsContentFrag : Fragment() {

    lateinit var currentView : View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        currentView = inflater.inflate(R.layout.news_content_frag, container, false)
        return currentView
    }

    /**
     * 提供给Activity的方法，刷新Fragment 更新新闻内容
     */
    fun refresh(title : String, content : String) {
        val contentLayout : LinearLayout = currentView.findViewById(R.id.contentLayout)
        val newsTitle : TextView = currentView.findViewById(R.id.newsTitle)
        val newsContent : TextView = currentView.findViewById(R.id.newsContent)

        contentLayout.visibility = View.VISIBLE
        newsTitle.text = title
        newsContent.text = content
    }
}
