package com.example.animalquizgame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.example.animalquizgame.databinding.ActivityHighScoreBinding
import com.example.animalquizgame.utlis.fileUtils.TextFileReader
import com.example.animalquizgame.utlis.fileUtils.TextFileWriter
import com.example.animalquizgame.utlis.uiUtils.TableSetter
import com.example.animalquizgame.viewmodels.HighScoreViewModel

class HighScore : AppCompatActivity() {

    lateinit var hVM:HighScoreViewModel
    lateinit var binding:ActivityHighScoreBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHighScoreBinding.inflate(layoutInflater)
        setContentView(binding.root)

        hVM = ViewModelProvider(this)[HighScoreViewModel::class.java]
        binding.viewModel = hVM
        binding.lifecycleOwner = this

        val textFileWriter = TextFileWriter()
        textFileWriter.writeTextFile(App.filename, this, "2000")
        val readTextFile = TextFileReader()
        readTextFile.readTextFile(App.filename, this)
        val scoreList = readTextFile.arrayList
        //scoreList must be even coz table have 2 columns
        if(scoreList.size % 2 != 0){
            scoreList.add(0)
        }
        val tableSetter = TableSetter(this, binding.tableHS,
            scoreList, binding.tVHighScore.typeface)
        tableSetter.generateTable(20)
    }



}