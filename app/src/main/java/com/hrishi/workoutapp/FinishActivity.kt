package com.hrishi.workoutapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.hrishi.workoutapp.databinding.ActivityFinishBinding
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*


class FinishActivity : AppCompatActivity() {
    private var binding:ActivityFinishBinding?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityFinishBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        setSupportActionBar(binding?.toolBar)
        if(supportActionBar!=null){
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        binding?.toolBar?.setNavigationOnClickListener {
            onBackPressed()
        }
        binding?.finishButton?.setOnClickListener {
            val intent= Intent(this@FinishActivity,MainActivity::class.java)
            startActivity(intent)
        }
        var historyDao=(application as WorkoutApp).db.historyDao()
        addDataToHistory(historyDao)
    }
    private fun addDataToHistory(historyDao: HistoryDao){
        val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss",Locale.getDefault())
        val currentDate:String = sdf.format(Date())
        lifecycleScope.launch {
            historyDao.insert(HistoryEntity(currentDate))
        }
    }
}