package com.example.helloworld.layout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import com.example.helloworld.R
import com.example.helloworld.uiBest.UIBestActivity

class LayoutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_layout)

        val testListView : Button = findViewById(R.id.btn_test_listView)
        testListView.setOnClickListener {
            startActivity(Intent(this,TestListViewActivity::class.java))
        }

        val fruitListViewActivity : Button = findViewById(R.id.btn_fruit_list_view)
        fruitListViewActivity.setOnClickListener {
            startActivity(Intent(this,FruitListViewActivity::class.java))
        }

        val recycleViewActivity : Button = findViewById(R.id.btn_fruit_recyclView)
        recycleViewActivity.setOnClickListener {
            startActivity(Intent(this,RecycleViewActivity::class.java))
        }

        val recycleViewVerticalActivity : Button = findViewById(R.id.btn_fruit_recyclView_v)
        recycleViewVerticalActivity.setOnClickListener {
            startActivity(Intent(this,RecycleViewVerticalActivity::class.java))
        }

        val staggeredGridActivity : Button = findViewById(R.id.btn_fruit_recyclView_stg)
        staggeredGridActivity.setOnClickListener {
            startActivity(Intent(this,StaggeredGridActivity::class.java))
        }

        val gridActivity : Button = findViewById(R.id.btn_fruit_recyclView_grid)
        gridActivity.setOnClickListener {
            startActivity(Intent(this,GridActivity::class.java))
        }

        val uiBestActivity : Button = findViewById(R.id.btn_9patch_chat)
        uiBestActivity.setOnClickListener {
            startActivity(Intent(this,UIBestActivity::class.java))
        }

        val btn_open_customerViews : Button = findViewById(R.id.btn_open_customerViews)
        btn_open_customerViews.setOnClickListener {
            startActivity(Intent(this, CustomViewsActivity:: class.java))
        }
    }
}