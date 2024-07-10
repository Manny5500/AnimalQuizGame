package com.example.animalquizgame

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.animalquizgame.databinding.ActivityHighScoreBinding
import com.example.animalquizgame.utlis.uiUtils.TableSetter
import com.example.animalquizgame.utlis.uiUtils.general.SetRootBackground
import com.example.animalquizgame.utlis.uiUtils.textViews.SetThemeColorText
import com.example.animalquizgame.viewmodels.HighScoreViewModel

class HighScore : BaseActivity() {

    private lateinit var hVM:HighScoreViewModel
    private lateinit var binding:ActivityHighScoreBinding
    private lateinit var sharedPreferences: SharedPreferences
    private var themeValue = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHighScoreBinding.inflate(layoutInflater)
        setContentView(binding.root)

        hVM = ViewModelProvider(this)[HighScoreViewModel::class.java]
        binding.viewModel = hVM
        binding.lifecycleOwner = this

        setSharedPreferences()
        SetRootBackground.setbg(themeValue, this, binding.root)

        hVM.init(this)
        hVM.readTextFile()
        observers()
        btnEvent()


    }

    private fun observers(){
        hVM.getScoreList().observe(this){scoreList->
            displayTable(scoreList)
        }
    }

    private fun btnEvent(){
        binding.btnResetScores.setOnClickListener{
            hVM.resetTextFile()
        }
    }

    private fun displayTable(scoreList:ArrayList<Int>){

        val textColor = SetThemeColorText.setColor(themeValue, this)
        if(scoreList.size % 2 != 0){
            scoreList.add(0)
        }
        val tableSetter = TableSetter(this, binding.tableHS,
            scoreList, binding.tVHighScore.typeface, textColor)
        tableSetter.generateTable(20)
    }

    private fun setSharedPreferences(){
        sharedPreferences = getSharedPreferences("SETTINGS", Context.MODE_PRIVATE)
        themeValue = sharedPreferences.getInt("theme", 0)
    }

}