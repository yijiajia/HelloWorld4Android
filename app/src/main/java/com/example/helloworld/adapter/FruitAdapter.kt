package com.example.helloworld.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.helloworld.R
import com.example.helloworld.model.Fruit

class FruitAdapter(activity : Activity, val resourceId : Int, data : List<Fruit>) : ArrayAdapter<Fruit>(activity,resourceId,data){

    // 内部类，用于缓存自定义的控件
    inner class ViewHolder(val fruitImage : ImageView, val fruitName : TextView)


    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view : View
        val viewHolder : ViewHolder
        if(convertView == null) {   // 判断当前是否有缓存view视图，没有则重新加载并保存在view中
            view = LayoutInflater.from(context).inflate(resourceId,parent,false)
            val fruitImage : ImageView = view.findViewById(R.id.fruitImage)
            val fruitName : TextView = view.findViewById(R.id.fruitName)
            viewHolder = ViewHolder(fruitImage, fruitName)
            view.tag = viewHolder
        }else {
            view = convertView
            viewHolder = view.tag as ViewHolder
        }

        val fruit = getItem(position)
        if (fruit != null) {
            viewHolder.fruitImage.setImageResource(fruit.imageId)
            viewHolder.fruitName.text = fruit.name
        }

        return view
    }
}