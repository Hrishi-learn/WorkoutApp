package com.hrishi.workoutapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.hrishi.workoutapp.databinding.ActivityBmiCalculatorBinding

class BmiCalculator : AppCompatActivity() {
    private var binding:ActivityBmiCalculatorBinding?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityBmiCalculatorBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        //code for the toolbar
        setSupportActionBar(binding?.toolBar)
        if(supportActionBar!=null){
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        binding?.toolBar?.setNavigationOnClickListener {
            onBackPressed()
        }
        //code for calculating and displaying bmi
        calculateBmi()
        binding?.radioButtonMetric?.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked){
                binding?.llTextInputHeightUS?.visibility=View.INVISIBLE
                binding?.textInputHeight?.visibility=View.VISIBLE
                binding?.etInputWeight?.hint="Weight(kg)"
            }
        }
        binding?.radioButtonUs?.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked){
                binding?.llTextInputHeightUS?.visibility=View.VISIBLE
                binding?.textInputHeight?.visibility=View.INVISIBLE
                binding?.etInputWeight?.hint="Weight(lbs)"
            }
        }
    }
    private fun calculateBmi(){
        binding?.bmiCalculateBtn?.setOnClickListener {
            if(binding?.radioButtonUs?.isChecked==true){
                if(binding?.etInputWeight?.text!!.isEmpty() || binding?.etInputHeightFeet?.text!!.isEmpty()
                    || binding?.etInputHeightInches?.text!!.isEmpty()){
                    Toast.makeText(this, "Please fill the above details", Toast.LENGTH_SHORT).show()
                }
                else if(binding?.etInputHeightFeet?.text.toString().toDouble()==0.0){
                    Toast.makeText(this, "Please provide valid inputs", Toast.LENGTH_SHORT).show()
                }
                else{
                    var weight=binding?.etInputWeight?.text.toString().toDouble()
                    var feet=binding?.etInputHeightFeet?.text.toString().toDouble()
                    var inches=binding?.etInputHeightInches?.text.toString().toDouble()
                    var height=12.0*feet+inches
                    var output=(weight/(height*height))*703
                    bmiOutput(output)
                }
            }
            else if(binding?.radioButtonMetric?.isChecked==true){
                if(binding?.etInputWeight?.text!!.isEmpty() || binding?.etInputHeight?.text!!.isEmpty()){
                    Toast.makeText(this, "Please fill the above details", Toast.LENGTH_SHORT).show()
                }
                else if(binding?.etInputHeight?.text.toString().toDouble()==0.0){
                    Toast.makeText(this, "Please provide valid inputs", Toast.LENGTH_SHORT).show()
                }
                else{
                    var weight=binding?.etInputWeight?.text.toString().toDouble()
                    var height=binding?.etInputHeight?.text.toString().toDouble()
                    var output=(weight/(height*height))
                    bmiOutput(output)
                }
            }
        }

    }
    private fun bmiOutput(output:Double){
        var newOutput=String.format("%.1f", output)
        if(output<18.5){
            binding?.bmiOutput?.text=newOutput
            binding?.bmiResultVal?.text="UnderWeight"
            binding?.bmiAdvice?.text="You are underweight. Take good Nutrition"
            binding?.bmiResults?.visibility=View.VISIBLE
        }
        else if(output>=25.0){
            binding?.bmiOutput?.text=newOutput
            binding?.bmiResultVal?.text="OverWeight"
            binding?.bmiAdvice?.text="You are Overweight. Take good Nutrition"
            binding?.bmiResults?.visibility=View.VISIBLE
        }
        else{
            binding?.bmiOutput?.text=newOutput
            binding?.bmiResultVal?.text="Normal Weight"
            binding?.bmiAdvice?.text="You are Normal weight. Keep up the good work"
            binding?.bmiResults?.visibility=View.VISIBLE
        }
    }

}
