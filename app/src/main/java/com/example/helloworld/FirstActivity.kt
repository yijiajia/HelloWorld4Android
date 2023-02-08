package com.example.helloworld

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.helloworld.lifycycle.DialogActivity
import com.example.helloworld.lifycycle.LifeActivity
import com.example.helloworld.lifycycle.NormalActivity

class FirstActivity: AppCompatActivity() {

    /**
     * 启动时创建
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.first_layout)
        val button1 : Button = findViewById(R.id.button1)
        button1.setOnClickListener{
            Toast.makeText(this,"打开second_activity",Toast.LENGTH_SHORT).show()
//            finish()    // 销毁activity
            // 点击该按钮启动意图，即打开SecondActivity对应的视图
//            val intent = Intent(this,SecondActivity::class.java)
            val intent = Intent("点击")
//            intent.addCategory("") // 添加类别
            startActivity(intent)
        }

        val baidu : Button = findViewById(R.id.button_bro)
        baidu.setOnClickListener{
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("https://www.baidu.com")
            startActivity(intent)
        }

        val tel : Button = findViewById(R.id.button_tel)
        tel.setOnClickListener{
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:10086")
            startActivity(intent)
        }

        val life : Button = findViewById(R.id.button_life)
        life.setOnClickListener{
            startActivity(Intent(this,LifeActivity::class.java))
        }

        val button2 : Button = findViewById(R.id.button_2)
        button2.setOnClickListener {
            val intent = Intent(this,SecondActivity::class.java)
            intent.putExtra("data1","hello")
            intent.putExtra("data2","second activity")
            startActivityForResult(intent,1)
        }

        val button4 : Button = findViewById(R.id.button_4)
        button4.setOnClickListener {
            FourActivity.actionStart(this,"hello","world")
        }
    }

    /**
     * 使用 startActivityForResult 启动页面后接受目标Activity返回的数据
     * 使用 requestCode请求码来判断是哪一个Activity返回的数据，以便处理相应的逻辑
     */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode) {
            1 -> if (resultCode == RESULT_OK) {
                val requestData = data?.getStringExtra("data_return")
                Log.d("FirstActivity","secondActivity 返回的数据为$requestData")
            }
        }
    }

    /**
     * 创建菜单
     */
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // 调用父类的getMenuInflater()方法获取MenuInflater对象，接着调用inflate方法，传递menu菜单id及对象
        menuInflater.inflate(R.menu.main,menu)
        return true
    }

    /**
     * 菜单下拉选中的逻辑
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.add_item -> Toast.makeText(this,"your click add item",Toast.LENGTH_SHORT).show()
            R.id.remove_item -> Toast.makeText(this,"your click remove item",Toast.LENGTH_SHORT).show()
        }
        return true
    }
}