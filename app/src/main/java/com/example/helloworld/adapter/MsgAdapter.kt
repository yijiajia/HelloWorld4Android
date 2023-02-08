package com.example.helloworld.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.helloworld.R
import com.example.helloworld.holder.LeftViewHolder
import com.example.helloworld.holder.RightViewHolder
import com.example.helloworld.model.Msg

class MsgAdapter(val msgList: List<Msg>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemViewType(position: Int): Int {
        val msg = msgList[position]
        return msg.type
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = if(viewType == Msg.TYPE_RECEIVED) {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.msg_left_item,parent,false)
        LeftViewHolder(view)
    }else {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.msg_right_item,parent,false)
        RightViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val msg = msgList[position]
        when (holder) {
            is LeftViewHolder -> holder.leftMsg.text = msg.content
            is RightViewHolder -> holder.rightMsg.text = msg.content
        }
    }

    override fun getItemCount() = msgList.size
}