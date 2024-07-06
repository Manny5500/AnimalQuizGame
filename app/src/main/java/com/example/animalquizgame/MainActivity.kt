package com.example.animalquizgame

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

    lateinit var mQVM: QuizItemViewModel
    lateinit var binding:ActivityMainBinding
    lateinit var buttonList:Array<MaterialButton>
    lateinit var tVM:TimerViewModel
    var themeblack:Int  = 0
    var  themeblue:Int = 0

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
                resetButtonColor()
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

        mQVM.getIsFinished().observe(this){bool ->
            if(bool) finish()
        }

        tVM.getTimeFormatted().observe(this){str->
            binding.textViewTimer.text = str
        }

        tVM.getIsRush().observe(this){bool->
            if(bool) binding.textViewTimer.setTextColor(Color.RED)
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

    private fun changeButtonColor(currentButton:MaterialButton){
        for(button in buttonList){
            if(button == currentButton){
                button.setBackgroundColor(themeblue)
            }else{
                button.setBackgroundColor(themeblack)
            }
        }
    }

    private fun resetButtonColor(){
        for(button in buttonList){
            button.setBackgroundColor(themeblack)
        }
    }

}


