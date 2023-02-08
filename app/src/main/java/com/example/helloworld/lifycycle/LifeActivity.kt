package com.example.helloworld.lifycycle

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Button
import com.example.helloworld.R

class LifeActivity : AppCompatActivity() {

    private val tag = "LifeActivity"

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_life)

        val normalActivity : Button = findViewById(R.id.button_normal)
        normalActivity.setOnClickListener {
            startActivity(Intent(this,NormalActivity::class.java))
        }

        val dialogActivity : Button = findViewById(R.id.button_dialog)
        dialogActivity.setOnClickListener{
            startActivity(Intent(this,DialogActivity::class.java))
        }
        Log.d(tag,"onCreate")
        if (savedInstanceState != null) {
            val tempData = savedInstanceState.getString("key")
            Log.d(tag,"tempData is $tempData")
        }
    }


    override fun onStart() {
        super.onStart()
        Log.d(tag,"onStart")
    }


    override fun onResume() {
        super.onResume()
        Log.d(tag,"onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(tag,"onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(tag,"onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(tag,"onDestroy")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(tag,"onRestart")
    }

    /**
     * activity 回收之前会被调用，通过 Bundle 对象来保存数据
     */
    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        outState.putString("key","临时数据")
    }
}