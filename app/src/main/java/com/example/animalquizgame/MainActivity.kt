package com.example.animalquizgame

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.animalquizgame.databinding.ActivityMainBinding
import com.example.animalquizgame.viewmodels.QuizItemViewModel
import com.example.animalquizgame.viewmodels.TimerViewModel
import com.google.android.material.button.MaterialButton
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var mQVM: QuizItemViewModel
    private lateinit var binding:ActivityMainBinding
    private lateinit var buttonList:Array<MaterialButton>
    private lateinit var tVM:TimerViewModel
    private var themeblack:Int  = 0
    private var  themeblue:Int = 0
    private var themered:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(getLayoutInflater())
        setContentView(binding.root)
        mQVM = ViewModelProvider(this)[QuizItemViewModel::class.java]
        tVM = ViewModelProvider(this)[TimerViewModel::class.java]
        binding.viewModel = mQVM
        binding.lifecycleOwner = this

        themeblue = ContextCompat.getColor(this, R.color.themeblue)
        themeblack = ContextCompat.getColor(this, R.color.themeblack)
        themered = ContextCompat.getColor(this, R.color.themered)

        mQVM.init()
        tVM.init()
        buttonList = arrayOf(binding.button1, binding.button2, binding.button3, binding.button4)
        observers()
        buttonClick()
    }
    private fun observers(){
        mQVM.getHasAnswer().observe(this){bool ->
            if(!bool){
                Snackbar.make(binding.root, "Please Choose an answer", Snackbar.LENGTH_SHORT)
                    .setAction("") {
                    }
                    .show()
            }else{
                changeButtonColor(null)
            }
        }
        mQVM.getMyImage().observe(this) {str ->
            Glide.with(this)
                .load(str)
                .into(binding.imgViewPic)
        }

        mQVM.getQuizOptions().observe(this){strArr->
            for((i, button) in buttonList.withIndex()){
                button.text = strArr[i]
            }
        }

        tVM.getTimeFormatted().observe(this){str->
            binding.textViewTimer.text = str
        }

        tVM.getIsRush().observe(this){bool->
            if(bool){
                binding.textViewTimer.setTextColor(themered)
                binding.textViewTimer.setBackgroundResource(R.drawable.timer_style_final)
            }
        }

        tVM.getIsTimeFinished().observe(this){bool->
            if(bool) goToScorePage()
        }
        mQVM.getIsFinished().observe(this){bool ->
            if(bool) {
                tVM.stopTimer()
                goToScorePage()
            }
        }

    }
    private fun buttonClick(){
        var currentButton:MaterialButton
        for(button in buttonList){
            button.setOnClickListener {
                currentButton = button
                mQVM.setAnswer(button.text.toString())
                changeButtonColor(currentButton)
             }
        }
    }

    private fun changeButtonColor(currentButton: MaterialButton?){
        for(button in buttonList){
            if(button == currentButton){
                button.setBackgroundColor(themeblue)
            }else{
                button.setBackgroundColor(themeblack)
            }
        }
    }
    private fun goToScorePage(){
        val intent = Intent(this, ScoreActivity::class.java)
        intent.putExtra("playerAnswers", mQVM.getCurrentAnswers())
        intent.putExtra("remainingTime", tVM.getRemainingTime())
        intent.putExtra("keyAnswers", mQVM.getKeyAnswers())
        intent.putExtra("totalTime", tVM.getTotalTime())
        startActivity(intent)
        finish()
    }
}


