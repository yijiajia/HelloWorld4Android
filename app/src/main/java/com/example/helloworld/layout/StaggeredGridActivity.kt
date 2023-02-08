package com.example.helloworld.layout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.helloworld.R
import com.example.helloworld.adapter.FruitRecycleStgAdapter
import com.example.helloworld.adapter.FruitRecycleVerticalAdapter
import com.example.helloworld.model.Fruit

class StaggeredGridActivity : AppCompatActivity() {

    private val fruitList = ArrayList<Fruit>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_staggered_grid)

        initFruits()

        /**
         * 设置排3列，纵向排列
         */
        val layoutManager = StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL)
        val recyclerViewStg : RecyclerView = findViewById(R.id.recycleView_stg)
        recyclerViewStg.layoutManager = layoutManager

        val adapter = FruitRecycleStgAdapter(fruitList)
        recyclerViewStg.adapter = adapter

    }


    private fun initFruits() {
        repeat(4) {
            fruitList.add(Fruit(getRandomLenStr("Apple"), R.drawable.apple_pic))
            fruitList.add(Fruit(getRandomLenStr("Banana"), R.drawable.banana_pic))
            fruitList.add(Fruit(getRandomLenStr("Orange"), R.drawable.orange_pic))
            fruitList.add(Fruit(getRandomLenStr("Watermelon"), R.drawable.watermelon_pic))
            fruitList.add(Fruit(getRandomLenStr("Pear"), R.drawable.pear_pic))
            fruitList.add(Fruit(getRandomLenStr("Grape"), R.drawable.grape_pic))
            fruitList.add(Fruit(getRandomLenStr("Pineapple"), R.drawable.pineapple_pic))
            fruitList.add(Fruit(getRandomLenStr("Strawberry"), R.drawable.strawberry_pic))
            fruitList.add(Fruit(getRandomLenStr("Cherry"), R.drawable.cherry_pic))
            fruitList.add(Fruit(getRandomLenStr("Mango"), R.drawable.mango_pic))
        }
    }

    private fun getRandomLenStr(str : String) : String {
        val n = (1..20).random()
        val builder = StringBuilder()
        repeat(n) {
            builder.append(str)
        }
        return builder.toString()
    }
}