package com.example.helloworld.uiBest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.helloworld.R
import com.example.helloworld.adapter.MsgAdapter
import com.example.helloworld.model.Msg

class UIBestActivity : AppCompatActivity() {

    private val msgList = ArrayList<Msg>()
//    private var adapter : MsgAdapter? = null
    private lateinit var adapter : MsgAdapter   // 使用 lateinit 延迟加载

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_uibest)

        initMsg()
        if(!::adapter.isInitialized) {  // 判断是否已经初始化
            adapter = MsgAdapter(msgList)
        }

        val layoutManager = LinearLayoutManager(this)
        val uiBestRecycleView : RecyclerView = findViewById(R.id.UIBestRecycleView)
        uiBestRecycleView.layoutManager = layoutManager
        uiBestRecycleView.adapter = adapter

        val send : Button = findViewById(R.id.btn_send)
        send.setOnClickListener {
            onClick(send)
        }
    }

    /**
     * 获取输入框的内容，如果内容不为空则创建消息实体；
     * 将消息实体添加进消息列表；
     * 刷新RecycleView中的显示；
     * 将RecycleView刷新到最后一行
     * 清空输入框的内容
     */
    fun onClick(v : View?) {
        val send : Button = findViewById(R.id.btn_send)
        when(v) {
            send -> {
                val inputText : EditText = findViewById(R.id.inputText)
                val contentText = inputText.text.toString()
                if(contentText.isNotEmpty()) {
                    val msg = Msg(contentText,Msg.TYPE_SEND)
                    msgList.add(msg)
//                    adapter?.notifyItemInserted(msgList.size - 1) // 当有新消息时，刷新RecycleView中的显示
                    adapter.notifyItemInserted(msgList.size - 1) // 当有新消息时，刷新RecycleView中的显示
                    val uiBestRecycleView : RecyclerView = findViewById(R.id.UIBestRecycleView)
                    uiBestRecycleView.scrollToPosition(msgList.size - 1) // 将RecycleView滑动至最新一行
                    inputText.setText("")   // 清空输入框的内容
                }
            }
        }
    }

    private fun initMsg() {
        msgList.add(Msg("Hello guy",Msg.TYPE_RECEIVED))
        msgList.add(Msg("Hello,What the fuck?",Msg.TYPE_SEND))
        msgList.add(Msg("This is Tom, Nice to meet you",Msg.TYPE_RECEIVED))
    }
}