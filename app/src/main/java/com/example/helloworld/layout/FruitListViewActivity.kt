package com.example.helloworld.layout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import android.widget.Toast
import com.example.helloworld.R
import com.example.helloworld.adapter.FruitAdapter
import com.example.helloworld.model.Fruit

class FruitListViewActivity : AppCompatActivity() {

    private val fruitList = ArrayList<Fruit>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fruit_list_view)
        initFruits()
        val adapter = FruitAdapter(this,R.layout.fruit_item,fruitList)
        val listView : ListView = findViewById(R.id.listview)
        listView.adapter = adapter
        listView.setOnItemClickListener { parent, view, pos, id ->
            val fruit = fruitList[pos]
            Toast.makeText(this, fruit.name, Toast.LENGTH_SHORT).show()
        }
    }

    private fun initFruits() {
        repeat(2) {
            fruitList.add(Fruit("Apple",R.drawable.apple_pic))
            fruitList.add(Fruit("Banana",R.drawable.banana_pic))
            fruitList.add(Fruit("Orange",R.drawable.orange_pic))
            fruitList.add(Fruit("Watermelon",R.drawable.watermelon_pic))
            fruitList.add(Fruit("Pear",R.drawable.pear_pic))
            fruitList.add(Fruit("Grape",R.drawable.grape_pic))
            fruitList.add(Fruit("Pineapple",R.drawable.pineapple_pic))
            fruitList.add(Fruit("Strawberry",R.drawable.strawberry_pic))
            fruitList.add(Fruit("Cherry",R.drawable.cherry_pic))
            fruitList.add(Fruit("Mango",R.drawable.mango_pic))
        }
    }
}