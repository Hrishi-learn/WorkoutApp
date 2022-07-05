package com.hrishi.workoutapp

import android.app.Dialog
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.hrishi.workoutapp.databinding.ActivityExerciseBinding
import com.hrishi.workoutapp.databinding.BackpressedCustomDialogBinding
import java.util.*
import java.util.concurrent.CompletionService
import kotlin.collections.ArrayList

class ExerciseActivity : AppCompatActivity(), TextToSpeech.OnInitListener {
    private var binding:ActivityExerciseBinding?=null
    private var countDownTimer:CountDownTimer?=null
    private var prog:Int=0
    private var count=10
    private var exercises:ArrayList<ExerciseModel>?=null
    private var exerciseCount=-1
    //creating variable for texttospeech
    private var tts:TextToSpeech?=null
    //adding sound effect
    private var player:MediaPlayer?=null
    //Adapter for recyclerView
    private var exerciseAdapter:ExerciseActivityStatus?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityExerciseBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        setSupportActionBar(binding?.toolBar)

        exercises=Constants.getExercises()

        if(supportActionBar!=null){
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        binding?.toolBar?.setNavigationOnClickListener {
            onBackPressed()
        }
        startTimer()
        tts= TextToSpeech(this,this)
        startRecyclerView()
    }
    private fun startRecyclerView(){
        binding?.recyclerViewList?.layoutManager=LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        exerciseAdapter=ExerciseActivityStatus(exercises!!)
        binding?.recyclerViewList?.adapter=exerciseAdapter
    }

    override fun onBackPressed() {
        customBackPressedDialog()
    }
    private fun customBackPressedDialog(){
        val backPressedDialog=Dialog(this)
        var backPressedBinding=BackpressedCustomDialogBinding.inflate(layoutInflater)
        backPressedDialog.setContentView(backPressedBinding.root)
        backPressedDialog.setCanceledOnTouchOutside(false)
        backPressedBinding.llQuitYes.setOnClickListener {
            this@ExerciseActivity.finish()
            backPressedDialog.dismiss()
        }
        backPressedBinding.llQuitNo.setOnClickListener {
            backPressedDialog.dismiss()
        }
        backPressedDialog.show()
    }

    private fun startNewTimer(){
        if(countDownTimer!=null){
            countDownTimer?.cancel()
            prog=0
        }
        binding?.flTimer?.visibility= View.INVISIBLE
        binding?.mainText?.visibility=View.INVISIBLE
        binding?.upcomingExercise?.visibility=View.INVISIBLE
        binding?.upcomingExerciseName?.visibility=View.INVISIBLE

        binding?.flTimerExercise?.visibility=View.VISIBLE
        binding?.imageView?.visibility=View.VISIBLE
        binding?.tvExerciseName?.visibility=View.VISIBLE

        binding?.imageView?.setImageResource(exercises!![exerciseCount].getImage())
        binding?.tvExerciseName?.text=exercises!![exerciseCount].getName()
        speak(exercises!![exerciseCount].getName())

        countDownTimer=object:CountDownTimer((count*1000).toLong(),1000){
            override fun onTick(millisUntilFinished: Long) {
                prog++
                binding?.tvTimerExercise?.text="${count-prog}"
                binding?.progressBarExercise?.progress= (count-prog).toInt()
            }
            override fun onFinish() {
                exercises!![exerciseCount].setIsCompleted(true)
                exercises!![exerciseCount].setIsSelected(false)
                exerciseAdapter?.notifyDataSetChanged()

                if(exerciseCount==exercises!!.size-1){
                    val intent=Intent(this@ExerciseActivity,FinishActivity::class.java)
                    startActivity(intent)
                }else{
                    count=10
                    prog=0
                    startTimer()
                }
            }
        }
        countDownTimer?.start()
    }

    private fun startTimer(){
        if(countDownTimer!=null){
            countDownTimer?.cancel()
            prog=0
        }
        try{
            val soundUri= Uri.parse("android.resource://com.hrishi.workoutapp/" + R.raw.app_src_main_res_raw_press_start)
            player=MediaPlayer.create(this,soundUri)
            player?.isLooping=false
            player?.start()
        }
        catch(e:Exception){
            e.printStackTrace()
        }

        binding?.flTimer?.visibility= View.VISIBLE
        binding?.mainText?.visibility=View.VISIBLE
        binding?.upcomingExercise?.visibility=View.VISIBLE
        binding?.upcomingExerciseName?.visibility=View.VISIBLE
        binding?.flTimerExercise?.visibility=View.INVISIBLE
        binding?.imageView?.visibility=View.INVISIBLE
        binding?.tvExerciseName?.visibility=View.INVISIBLE

        binding?.upcomingExerciseName?.text=exercises!![exerciseCount+1].getName()

        countDownTimer=object:CountDownTimer((count*1000).toLong(),1000){
            override fun onTick(millisUntilFinished: Long) {
                prog++
                binding?.tvTimer?.text="${count-prog}"
                binding?.progressBar?.progress= (count-prog).toInt()
            }
            override fun onFinish() {
                exerciseCount++
                exercises!![exerciseCount].setIsSelected(true)
                exerciseAdapter?.notifyDataSetChanged()
                count=30
                prog=0
                startNewTimer()
            }
        }
        countDownTimer?.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        if(countDownTimer!=null){
            countDownTimer?.cancel()
            tts?.stop()
            tts?.shutdown()
            prog=0
        }
        binding=null
    }

    private fun speak(text:String){
        tts?.speak(text,TextToSpeech.QUEUE_FLUSH,null,"")
    }
    override fun onInit(status: Int) {
        if(status==TextToSpeech.SUCCESS){
            val result=tts?.setLanguage(Locale.JAPAN)
            if(result==TextToSpeech.LANG_NOT_SUPPORTED || result==TextToSpeech.LANG_MISSING_DATA){
                Log.e("TexttoSpeech","Language Not Supported")
            }
        }else{
            Log.e("TextToSpeech","Initialisation went wrong")
        }
    }
}