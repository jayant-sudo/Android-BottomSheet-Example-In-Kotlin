package com.codingwithjks.bottomsheet.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.codingwithjks.bottomsheet.Listener.Listener
import com.codingwithjks.bottomsheet.Model.Food
import com.codingwithjks.bottomsheet.R


class FoodAdapter(private val context:Context, var foodList:ArrayList<Food>, private var listener: Listener) : RecyclerView.Adapter<FoodAdapter.FoodViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        return FoodViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.each_row,parent,false))
    }

    override fun getItemCount(): Int =foodList.size

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        var food=foodList[position]
        holder.name.text=food.name
        holder.price.text="₹${food.price}"
        Glide.with(context)
            .load(food.image).into(holder.image)
    }


    inner class FoodViewHolder(itemView:View): RecyclerView.ViewHolder(itemView){
        var name:TextView=itemView.findViewById(R.id.name)
        var image:ImageView=itemView.findViewById(R.id.image)
        var price:TextView=itemView.findViewById(R.id.price)

        init {
            itemView.setOnClickListener {
                listener.onClickListener(adapterPosition)
            }
        }

    }
}