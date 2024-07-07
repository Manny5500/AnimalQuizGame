package com.example.animalquizgame

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.animalquizgame.databinding.ActivityScoreBinding
import com.example.animalquizgame.viewmodels.ScoreViewModel

class ScoreActivity : AppCompatActivity() {
    private lateinit var sVM:ScoreViewModel
    private lateinit var binding:ActivityScoreBinding
    private lateinit var keyAnswers:Array<String>
    private lateinit var playerAnswers:Array<String>
    private var remainingTime:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScoreBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sVM = ViewModelProvider(this)[ScoreViewModel::class.java]
        binding.viewModel = sVM
        binding.lifecycleOwner = this

        setValueFromIntents()
        sVM.init(remainingTime, playerAnswers, keyAnswers)
        displayUI()
        buttonsEvent()
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
        binding.textViewStats.setShadowLayer(1F, 12F,
            5F, Color.parseColor(sVM.hexColor))
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

}