package com.example.animalquizgame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.animalquizgame.databinding.ActivityHighScoreBinding
import com.example.animalquizgame.utlis.uiUtils.TableSetter
import com.example.animalquizgame.viewmodels.HighScoreViewModel

class HighScore : AppCompatActivity() {

    private lateinit var hVM:HighScoreViewModel
    private lateinit var binding:ActivityHighScoreBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHighScoreBinding.inflate(layoutInflater)
        setContentView(binding.root)

        hVM = ViewModelProvider(this)[HighScoreViewModel::class.java]
        binding.viewModel = hVM
        binding.lifecycleOwner = this

        hVM.init(this)
        hVM.readTextFile()

        hVM.getScoreList().observe(this){scoreList->
            displayTable(scoreList)
        }

        binding.btnResetScores.setOnClickListener{
            hVM.resetTextFile()
        }

    }

    private fun displayTable(scoreList:ArrayList<Int>){
        if(scoreList.size % 2 != 0){
            scoreList.add(0)
        }
        val tableSetter = TableSetter(this, binding.tableHS,
            scoreList, binding.tVHighScore.typeface)
        tableSetter.generateTable(20)
    }

}