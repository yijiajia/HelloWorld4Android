package com.example.helloworld.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.helloworld.R
import com.example.helloworld.material.FruitItemMainActivity
import com.example.helloworld.model.Fruit

class CardFruitRecycleAdapter(val context : Context, val fruitList: List<Fruit>) : RecyclerView.Adapter<CardFruitRecycleAdapter.ViewHolder>(){

    inner class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        val fruitImage : ImageView = view.findViewById(R.id.card_fruitImage)
        val fruitName: TextView = view.findViewById(R.id.card_fruitName)
    }

    override fun onCreateViewHolder(parent: ViewGroup,  viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.fruit_item_card,parent,false)
        val holder = ViewHolder(view)
        holder.itemView.setOnClickListener{
            val position = holder.absoluteAdapterPosition
            val fruit = fruitList[position]
            val intent = Intent(context, FruitItemMainActivity::class.java).apply {
                putExtra(FruitItemMainActivity.FRUIT_NAME,fruit.name)
                putExtra(FruitItemMainActivity.FRUIT_IMAGE_ID,fruit.imageId)
            }
            context.startActivity(intent)
        }
        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val fruit = fruitList[position]
        holder.fruitName.text = fruit.name
        Glide.with(context).load(fruit.imageId).into(holder.fruitImage)
    }

    override fun getItemCount() = fruitList.size
}