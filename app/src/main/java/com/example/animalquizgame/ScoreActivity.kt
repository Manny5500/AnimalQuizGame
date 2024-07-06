package com.example.animalquizgame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.example.animalquizgame.databinding.ActivityScoreBinding
import com.example.animalquizgame.viewmodels.ScoreViewModel



class ScoreActivity : AppCompatActivity() {
    private lateinit var sVM:ScoreViewModel
    private lateinit var binding:ActivityScoreBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScoreBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sVM = ViewModelProvider(this)[ScoreViewModel::class.java]
        binding.viewModel = sVM
        binding.lifecycleOwner = this

        val keyAnswers = intent.getStringArrayExtra("keyAnswers")
        val playerAnswers = intent.getStringArrayExtra("playerAnswers")
        val remainingTime = intent.getIntExtra("remainingTime", 0)
        val totalTime =intent.getIntExtra("totalTime", 0)

        if (playerAnswers != null && keyAnswers != null) {
            sVM.init(remainingTime, playerAnswers, keyAnswers, totalTime)
            binding.textViewStats.text = sVM.status
            binding.textViewScore.text = ""+sVM.correctAnswer+"/11"
            binding.textViewFinalScore.text = ""+sVM.finalScore
        }




    }
}