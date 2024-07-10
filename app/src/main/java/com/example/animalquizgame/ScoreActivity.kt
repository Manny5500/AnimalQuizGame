package com.example.animalquizgame

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.animalquizgame.databinding.ActivityScoreBinding
import com.example.animalquizgame.utlis.uiUtils.general.SetRootBackground
import com.example.animalquizgame.viewmodels.ScoreViewModel

class ScoreActivity : BaseActivity() {
    private lateinit var sVM:ScoreViewModel
    private lateinit var binding:ActivityScoreBinding
    private lateinit var keyAnswers:Array<String>
    private lateinit var playerAnswers:Array<String>
    private var remainingTime:Int = 0
    private lateinit var sharedPreferences:SharedPreferences
    private var themeValue = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScoreBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sVM = ViewModelProvider(this)[ScoreViewModel::class.java]
        binding.viewModel = sVM
        binding.lifecycleOwner = this

        binding.textViewBestScore.visibility = View.GONE

        setSharedPreferences()
        SetRootBackground.setbg(themeValue, this, binding.root)
        setValueFromIntents()
        sVM.init(remainingTime, playerAnswers, keyAnswers, this)
        displayUI()
        buttonsEvent()
        sVM.readTextFile().setScoreList().compareScore()
        sVM.getIsHigher().observe(this){bool->
            if(bool) binding.textViewBestScore.visibility = View.VISIBLE
        }
        sVM.writeTextFile()
    }
    private fun setValueFromIntents(){
        keyAnswers = intent.getStringArrayExtra("keyAnswers")!!
        playerAnswers = intent.getStringArrayExtra("playerAnswers")!!
        remainingTime = intent.getIntExtra("remainingTime", 0)
    }
    @SuppressLint("SetTextI18n")
    fun displayUI(){
        binding.textViewStats.text = sVM.status
        binding.textViewScore.text = "${sVM.correctAnswer} / ${keyAnswers.size}"
        binding.textViewFinalScore.text = "${sVM.finalScore}"

        Glide.with(this)
            .load(sVM.imageUrl)
            .into(binding.imgViewPicActual)
    }
    private fun buttonsEvent(){
        binding.buttonMenu.setOnClickListener{
            finish()
        }
        binding.buttonNewGame.setOnClickListener{
            navigateTo(MainActivity::class.java)
        }
    }
    private fun navigateTo(cls: Class<*>){
        val intent = Intent(this, cls)
        startActivity(intent)
        finish()
    }
    private fun setSharedPreferences(){
        sharedPreferences = getSharedPreferences("SETTINGS", Context.MODE_PRIVATE)
        themeValue = sharedPreferences.getInt("theme", 0)
    }

}