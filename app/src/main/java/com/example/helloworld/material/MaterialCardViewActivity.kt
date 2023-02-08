package com.example.helloworld.material

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.helloworld.R
import com.example.helloworld.adapter.CardFruitRecycleAdapter
import com.example.helloworld.model.Fruit
import kotlin.concurrent.thread

class MaterialCardViewActivity : AppCompatActivity() {

    val fruits = mutableListOf(
        Fruit("Apple", R.drawable.apple),
        Fruit("Banana", R.drawable.banana),
        Fruit("Orange", R.drawable.orange),
        Fruit("Watermelon", R.drawable.watermelon),
        Fruit("Pear", R.drawable.pear),
        Fruit("Grape", R.drawable.grape),
        Fruit("Pineapple", R.drawable.pineapple),
        Fruit("Strawberry", R.drawable.strawberry),
        Fruit("Cherry", R.drawable.cherry),
        Fruit("Mango", R.drawable.mango)
    )
    val fruitList = ArrayList<Fruit>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_material_card_view)
        initFruits()
        val layoutManager = GridLayoutManager(this,2)
        val adapter = CardFruitRecycleAdapter(this, fruitList)

        val recyclerView : RecyclerView = findViewById(R.id.card_recycleView)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter

        val swipeRefresh : SwipeRefreshLayout = findViewById(R.id.card_swipeRefresh)
        swipeRefresh.setColorSchemeResources(R.color.teal_200) // 下拉刷新的颜色
        swipeRefresh.setOnRefreshListener { // 下拉事件监听
            refreshFruits(adapter)
        }

        val toolbar : Toolbar = findViewById(R.id.card_toolbar)
        setSupportActionBar(toolbar)
        // 给导航一个按钮
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setHomeAsUpIndicator(R.drawable.ic_menu)
        }
    }

    private fun initFruits() {
        fruitList.clear()
        repeat(50) {
            val index = (0 until fruits.size).random()
            fruitList.add(fruits[index])
        }
    }

    private fun refreshFruits(adapter: CardFruitRecycleAdapter) {
        thread {
            Thread.sleep(1000) // 模拟加载时长
            runOnUiThread {
                initFruits()
                adapter.notifyDataSetChanged()
                val swipeRefresh : SwipeRefreshLayout = findViewById(R.id.card_swipeRefresh)
                swipeRefresh.isRefreshing = false   // 取消刷新状态
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            android.R.id.home -> {
                val drawerLayout : DrawerLayout = findViewById(R.id.card_drawerLayout)
                drawerLayout.openDrawer(GravityCompat.START)
            }
        }

        return super.onOptionsItemSelected(item)
    }
}