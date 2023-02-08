package com.example.helloworld.holder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.helloworld.R

/**
 * 密封类
 */
sealed class MsgViewHolder
class LeftViewHolder(view : View) : RecyclerView.ViewHolder(view) {
    val leftMsg : TextView = view.findViewById(R.id.leftMsg)
}

class RightViewHolder(view : View) : RecyclerView.ViewHolder(view) {
    val rightMsg : TextView = view.findViewById(R.id.rightMsg)
}

