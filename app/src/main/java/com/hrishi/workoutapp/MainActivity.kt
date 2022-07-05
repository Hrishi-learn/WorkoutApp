package com.hrishi.workoutapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.Toast
import com.hrishi.workoutapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var mainBinding: ActivityMainBinding?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding?.root)
        mainBinding?.flStartBtn?.setOnClickListener{
            val intent = Intent(this,ExerciseActivity::class.java)
            startActivity(intent)
        }
        mainBinding?.bmiCalculator?.setOnClickListener {
            val newIntent=Intent(this,BmiCalculator::class.java)
            startActivity(newIntent)
        }
        mainBinding?.historyBtn?.setOnClickListener {
            val historyIntent=Intent(this,History::class.java)
            startActivity(historyIntent)
        }
    }
}