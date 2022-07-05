package com.hrishi.workoutapp

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.hrishi.workoutapp.databinding.ItemsExerciseBinding

class ExerciseActivityStatus(val items:ArrayList<ExerciseModel>):RecyclerView.Adapter<ExerciseActivityStatus.ViewHolder>(){

    class ViewHolder(binding:ItemsExerciseBinding):RecyclerView.ViewHolder(binding.root){
        var tvItem:TextView=binding.tvItem
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemsExerciseBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model:ExerciseModel=items[position]
        holder.tvItem.text=model.getId().toString()
        when{
            model.getIsSelected()->{
                holder.tvItem.background=ContextCompat.getDrawable(holder.itemView.context,R.drawable.items_bg_selected)
            }
            model.getIsCompleted()->{
                holder.tvItem.background=ContextCompat.getDrawable(holder.itemView.context,R.drawable.items_bg_completed)
                holder.tvItem.setTextColor(Color.parseColor("#FFFFFF"))
            }
        }
    }
    override fun getItemCount(): Int {
        return items.size
    }
}