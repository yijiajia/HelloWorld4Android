package com.example.helloworld.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.helloworld.R
import com.example.helloworld.model.Fruit

class FruitRecycleAdapter(val fruitList : List<Fruit>) :
    RecyclerView.Adapter<FruitRecycleAdapter.ViewHolder>() {

    inner class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        val fruitImage : ImageView = view.findViewById(R.id.fruitImage)
        val fruitName : TextView = view.findViewById(R.id.fruitName)
    }

    /**
     * 加载自定义的布局，创建ViewHolder实例
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fruit_item,parent,false)
        return ViewHolder(view)
    }

    /**
     * 对 RecycleView子项的数据进行赋值，会在每个子项被滚动到屏幕内的时候执行
     * 通过position来定位数据，
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val fruit = fruitList[position]
        holder.fruitImage.setImageResource(fruit.imageId)
        holder.fruitName.text = fruit.name
    }

    override fun getItemCount() = fruitList.size
}