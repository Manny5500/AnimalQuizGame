package com.example.animalquizgame

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.animalquizgame.databinding.ActivityMainBinding
import com.example.animalquizgame.utlis.uiUtils.buttons.ButtonEventColorizer
import com.example.animalquizgame.utlis.uiUtils.general.SetRootBackground
import com.example.animalquizgame.utlis.uiUtils.general.SetSnackBar
import com.example.animalquizgame.utlis.uiUtils.textViews.ShapeMaker
import com.example.animalquizgame.viewmodels.QuizItemViewModel
import com.example.animalquizgame.viewmodels.TimerViewModel
import com.google.android.material.button.MaterialButton
import com.google.android.material.snackbar.Snackbar

class MainActivity : BaseActivity() {

    private lateinit var mQVM: QuizItemViewModel
    private lateinit var binding:ActivityMainBinding
    private lateinit var buttonList:Array<MaterialButton>
    private lateinit var tVM:TimerViewModel
    private var btnClick:Int  = 0
    private var  btnUnclick:Int = 0
    private var themered:Int = 0
    private lateinit var sharedPreferences: SharedPreferences
    private var themeValue = 0
    private var time = 10
    private var quiz = 5
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mQVM = ViewModelProvider(this)[QuizItemViewModel::class.java]
        tVM = ViewModelProvider(this)[TimerViewModel::class.java]

        binding.viewModel = mQVM
        binding.lifecycleOwner = this

        setSharedPreferences()
        SetRootBackground.setbg(themeValue, this, binding.root)

        ShapeMaker.context = this
        ShapeMaker.theme = themeValue
        binding.textViewTimer.background = ShapeMaker.setShape()

        btnClick = ButtonEventColorizer.getClickedColor(themeValue,this)
        btnUnclick = ButtonEventColorizer.getUnclickedColor(themeValue,this)
        themered = ContextCompat.getColor(this, R.color.themered)

        mQVM.init(quiz)
        tVM.init(time)

        buttonList = arrayOf(binding.button1, binding.button2, binding.button3, binding.button4)
        observers()
        buttonClick()
    }
    private fun observers(){
        mQVM.getHasAnswer().observe(this){bool ->
            if(!bool){
                SetSnackBar.set(themeValue, this, binding.root, "Please Choose an answer")
                //Snackbar.make(binding.root, "Please Choose an answer", Snackbar.LENGTH_SHORT)
                  //  .show()
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
                button.setBackgroundColor(btnClick)
            }else{
                button.setBackgroundColor(btnUnclick)
            }
        }
    }
    private fun goToScorePage(){
        val intent = Intent(this, ScoreActivity::class.java)
        intent.putExtra("playerAnswers", mQVM.getCurrentAnswers())
        intent.putExtra("remainingTime", tVM.getRemainingTime())
        intent.putExtra("keyAnswers", mQVM.getKeyAnswers())
        startActivity(intent)
        finish()
    }

    private fun setSharedPreferences(){
        sharedPreferences = getSharedPreferences("SETTINGS", Context.MODE_PRIVATE)
        themeValue = sharedPreferences.getInt("theme", 0)
        time = sharedPreferences.getInt("time", 10)
        quiz = sharedPreferences.getInt("quiz", 5)
    }
}


