package com.example.helloworld.fragment.news

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.helloworld.R

class NewsTitleFrag : Fragment() {

    private var isTwoPane = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.news_title_frag,container,false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        isTwoPane = activity?.findViewById<View>(R.id.newsContentFrag) != null
        Log.d("","isTwoPane is $isTwoPane")
        val layoutManager = LinearLayoutManager(activity)
        val newsTitleRecyclerView : RecyclerView = activity?.findViewById(R.id.newsTitleRecyclerView) as RecyclerView
        newsTitleRecyclerView.layoutManager = layoutManager

        val adapter = NewsAdapter(getNews())
        newsTitleRecyclerView.adapter = adapter
    }

    fun getNews() : List<News> {
        val newsList = ArrayList<News>()
        for (i in 1..50) {
            val news = News("This is news title is $i",getRandomLengthString("This is news content is $i"))
            newsList.add(news)
        }
        return newsList
    }

    fun getRandomLengthString(str : String) : String{
        val n = (1..20).random()
        val builder = StringBuilder()
        repeat(n) {
            builder.append(str)
        }
        return builder.toString()
    }


    inner class NewsAdapter(val newsList: List<News>) : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {
        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val newsTitle : TextView = view.findViewById(R.id.newsTitle)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.news_item,parent,false)
            val holder = ViewHolder(view)
            holder.itemView.setOnClickListener {
                val news = newsList[holder.adapterPosition]
                if(isTwoPane) { // 双页模式
                    if(activity != null) {
                        val newsMainActivity = activity as NewsMainActivity
                        val newsContentFrag = newsMainActivity.supportFragmentManager.findFragmentById(R.id.newsContentFrag) as NewsContentFrag
                        newsContentFrag.refresh(news.title, news.content)
                    }
                }else {
                    NewsContentActivity.actionStart(parent.context, news.title, news.content)
                }
            }
            return holder
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val news = newsList[position]
            holder.newsTitle.text = news.title
        }

        override fun getItemCount() = newsList.size
    }
}