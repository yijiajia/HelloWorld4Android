package com.example.helloworld.material

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.bumptech.glide.Glide
import com.example.helloworld.R
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton

class FruitItemMainActivity : AppCompatActivity() {

    companion object {
        const val FRUIT_NAME = "fruit_name"
        const val FRUIT_IMAGE_ID = "fruit_image_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fruit_item_main)
        val toolbar : Toolbar = findViewById(R.id.fruit_toolbar)
        val collapsingToolbar : CollapsingToolbarLayout = findViewById(R.id.fruit_collapsingToolbar)
        val fruitImageView : ImageView = findViewById(R.id.fruitImageView)
        val fruitContentText : TextView = findViewById(R.id.fruitContentText)
        val fruitComment : FloatingActionButton = findViewById(R.id.fruit_comment)

        val fruitName = intent.getStringExtra(FRUIT_NAME) ?: ""
        val fruitImageId = intent.getIntExtra(FRUIT_IMAGE_ID,0)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)   // 显示Home图标
        collapsingToolbar.title = fruitName
        Glide.with(this).load(fruitImageId).into(fruitImageView)
        fruitContentText.text = generateFruitContent(fruitName)

        fruitComment.setOnClickListener{
            Toast.makeText(this,"评论功能正在施工中",Toast.LENGTH_SHORT).show()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            android.R.id.home -> {
                finish()
            }
        }
        return true
    }

    private fun generateFruitContent(fruitName : String) = fruitName.repeat(500)
}