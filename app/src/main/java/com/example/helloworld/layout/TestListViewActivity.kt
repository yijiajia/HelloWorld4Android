package com.example.helloworld.layout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import com.example.helloworld.R

class TestListViewActivity : AppCompatActivity() {

//    private val data = listOf("Apple","Banana","Orange","Watermelon","Pear","Grape","Pineapple","Strawberry","Cherry")
    private val data = ArrayList<String>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_list_view)

        initData()

        val listView : ListView = findViewById(R.id.test_listView)
        val adapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,data)
        listView.adapter = adapter

    }

    private fun initData() {
        repeat(2) {
            data.addAll(listOf("Apple","Banana","Orange","Watermelon","Pear","Grape","Pineapple","Strawberry","Cherry"))
        }
    }
}