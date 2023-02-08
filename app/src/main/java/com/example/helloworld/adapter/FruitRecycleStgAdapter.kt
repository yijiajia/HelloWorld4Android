package com.example.helloworld.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.helloworld.R
import com.example.helloworld.model.Fruit

    class FruitRecycleStgAdapter(val fruitList : List<Fruit>) :
        RecyclerView.Adapter<FruitRecycleStgAdapter.ViewHolder>() {

        inner class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {
            val fruitImage : ImageView = view.findViewById(R.id.fruitImage_stg)
            val fruitName : TextView = view.findViewById(R.id.fruitName_stg)
        }

        /**
         * 加载自定义的布局，创建ViewHolder实例
         */
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.fruit_item_stagger,parent,false)

            val viewHolder = ViewHolder(view)
            // 注册点击事件
            // 针对最外层子项注册点击事件
            viewHolder.itemView.setOnClickListener {
    //            val position = viewHolder.adapterPosition
                val position = viewHolder.bindingAdapterPosition
                val fruit = fruitList[position]
                Toast.makeText(parent.context, "you clicked View ${fruit.name}",Toast.LENGTH_SHORT).show()
            }

            // 针对image注册点击事件
            viewHolder.fruitImage.setOnClickListener {
                val position = viewHolder.adapterPosition
                val fruit = fruitList[position]
                Toast.makeText(parent.context, "you clicked Image ${fruit.name}",Toast.LENGTH_SHORT).show()
            }

            return viewHolder
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