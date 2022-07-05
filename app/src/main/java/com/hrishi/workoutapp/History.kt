package com.hrishi.workoutapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.hrishi.workoutapp.databinding.ActivityHistoryBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class History : AppCompatActivity() {
    private var binding:ActivityHistoryBinding?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        setSupportActionBar(binding?.toolBar)
        if(supportActionBar!=null){
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        binding?.toolBar?.setNavigationOnClickListener {
            onBackPressed()
        }
        val historyDao=(application as WorkoutApp).db.historyDao()
        showItemsInRecyclerView(historyDao);
    }
    private fun showItemsInRecyclerView(historyDao: HistoryDao) {
        lifecycleScope.launch {
            historyDao.fetchAllRecords().collect {
                if(it!=null){
                    binding?.tvNoActivity?.visibility=View.GONE
                    val arr=ArrayList(it)
                    val adapter=historyAdapter(arr)
                    binding?.rvHistory?.adapter=adapter
                    binding?.rvHistory?.layoutManager=LinearLayoutManager(this@History)
                }
            }
        }
    }
}