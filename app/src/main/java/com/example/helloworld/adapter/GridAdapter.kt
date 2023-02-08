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

class GridAdapter(val fruitList : List<Fruit>) : RecyclerView.Adapter<GridAdapter.ViewHolder>()  {

    inner class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        val fruitImage : ImageView = view.findViewById(R.id.fruitImage_grid)
        val fruitName : TextView = view.findViewById(R.id.fruitName_grid)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fruit_item_grid,parent,false)

        val viewHolder = ViewHolder(view)
        // 注册点击事件
        // 针对最外层子项注册点击事件
        viewHolder.itemView.setOnClickListener {
//            val position = viewHolder.adapterPosition
            val position = viewHolder.bindingAdapterPosition
            val fruit = fruitList[position]
            Toast.makeText(parent.context, "you clicked View ${fruit.name}", Toast.LENGTH_SHORT).show()
        }

        // 针对image注册点击事件
        viewHolder.fruitImage.setOnClickListener {
            val position = viewHolder.adapterPosition
            val fruit = fruitList[position]
            Toast.makeText(parent.context, "you clicked Image ${fruit.name}", Toast.LENGTH_SHORT).show()
        }

        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val fruit = fruitList[position]
        holder.fruitImage.setImageResource(fruit.imageId)
        holder.fruitName.text = fruit.name
    }

    override fun getItemCount() = fruitList.size


}