package com.example.helloworld.material

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.example.helloworld.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar

class ToolBarActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tool_bar)
        val toolbar : Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val fab : FloatingActionButton = findViewById(R.id.toolbar_fab)
        fab.setOnClickListener{ view ->
            Snackbar.make(view ,"假装要删除数据",Snackbar.LENGTH_SHORT)
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
        when(item.itemId) {
            R.id.backUp -> finish()
            R.id.delete -> Toast.makeText(this,"删除按钮",Toast.LENGTH_SHORT).show()
            R.id.settings -> Toast.makeText(this,"设置按钮",Toast.LENGTH_SHORT).show()
        }
        return true
    }
}