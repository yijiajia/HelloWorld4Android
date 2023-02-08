package com.example.helloworld.jetpack

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.edit
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.helloworld.R

/**
 * 使用ViewModel绑定数据
 */
class ViewModelCounterActivity : AppCompatActivity() {

    lateinit var viewModel: MyViewModel
    lateinit var sp : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_model_counter)
        // 注册观察者
        lifecycle.addObserver(MyObserver(lifecycle))
        lifecycle.addObserver(MyObserver4Interface())
        sp = getPreferences(MODE_PRIVATE)
        val plusButton : Button = findViewById(R.id.view_model_addCount)
        val infoText : TextView = findViewById(R.id.view_model_count)

//        viewModel = ViewModelProvider(this).get(MyViewModel::class.java)
        viewModel = ViewModelProvider(this,ViewModelFactory(sp.getInt("oldCount",0)))[MyViewModel::class.java]

        plusButton.setOnClickListener{
            viewModel.plus()
//            refreshCounter()
        }

        val clearButton : Button = findViewById(R.id.view_model_clear)
        clearButton.setOnClickListener {
            viewModel.clear()
//            refreshCounter()
        }
        // 数据变化监听
        viewModel.counter.observe(this) { count ->
            Toast.makeText(this,"数据发生变化",Toast.LENGTH_SHORT).show()
            infoText.text = count.toString()
        }
    }

    /*private fun refreshCounter() {
        val infoText : TextView = findViewById(R.id.view_model_count)
        infoText.text = viewModel.count.toString()
    }*/

    override fun onPause() {
        super.onPause()
        sp.edit {
            viewModel.counter.value?.let { putInt("oldCount", it) }
        }
    }
}