package com.hrishi.workoutapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hrishi.workoutapp.databinding.HistoryItemsBinding

class historyAdapter(val historyItems:ArrayList<HistoryEntity>):RecyclerView.Adapter<historyAdapter.ViewHolder>(){

    class ViewHolder(binding: HistoryItemsBinding):RecyclerView.ViewHolder(binding.root){
        var tvhistory=binding.tvHistory
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(HistoryItemsBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item=historyItems[position]
        holder.tvhistory.text=item.date
        if(position%2==0){
            holder.tvhistory.setBackgroundResource(R.drawable.history_items)
        }
    }
    override fun getItemCount(): Int {
       return historyItems.size
    }
}