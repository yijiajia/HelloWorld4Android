package com.example.helloworld.material

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.helloworld.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar

class DrawerLayoutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drawer_layout)

        // 使用ToolBar, 取消默认ActionBar
        val toolbar : Toolbar = findViewById(R.id.drawer_toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)  // 显示导航按钮
            it.setHomeAsUpIndicator(R.drawable.ic_menu) // 设置导航图标（如果不设置默认就是 ⬅ 返回图标
        }

        // 滑动导航里的菜单
        val navView : NavigationView = findViewById(R.id.navView)
        navView.setCheckedItem(R.id.navCall)    // 选中Call菜单项
        navView.setNavigationItemSelectedListener { // 设置菜单项的点击事件
           /* val drawerLayout : DrawerLayout = findViewById(R.id.drawerLayout)
            drawerLayout.closeDrawers() // 关闭滑动菜单
            */
            when(it.itemId) {
                R.id.navCall -> Toast.makeText(this,"这是电话号码",Toast.LENGTH_SHORT).show()
                R.id.navFriends -> Toast.makeText(this,"你没有朋友，别点了",Toast.LENGTH_SHORT).show()
                R.id.navLocation -> Toast.makeText(this,"你在哪？",Toast.LENGTH_SHORT).show()
                R.id.navMail -> Toast.makeText(this,"你的邮箱",Toast.LENGTH_SHORT).show()
                R.id.navTask -> Toast.makeText(this,"你的任务",Toast.LENGTH_SHORT).show()
            }
            true
        }

        // 悬浮按钮的点击事件
        val fab : FloatingActionButton = findViewById(R.id.drawer_fab)
        fab.setOnClickListener{ view ->
            Snackbar.make(view ,"假装要删除数据", Snackbar.LENGTH_SHORT)
                .setAction("Undo") {
                    Toast.makeText(this, "取消删除", Toast.LENGTH_SHORT).show()
                }
                .show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.backUp -> finish()
            R.id.delete -> Toast.makeText(this, "删除按钮", Toast.LENGTH_SHORT).show()
            R.id.settings -> Toast.makeText(this, "设置按钮", Toast.LENGTH_SHORT).show()
            // 点击home按钮，显示滑动菜单并在左侧显示
            android.R.id.home -> {
                val drawerLayout: DrawerLayout = findViewById(R.id.drawerLayout)
                drawerLayout.openDrawer(GravityCompat.START)
            }

        }
        return true
    }

}