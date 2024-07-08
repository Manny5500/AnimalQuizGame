package com.example.animalquizgame.viewmodels

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.animalquizgame.App
import com.example.animalquizgame.utlis.fileUtils.TextFileReader
import com.example.animalquizgame.utlis.fileUtils.TextFileResetter

class HighScoreViewModel: ViewModel(){
    private val scoreList:MutableLiveData<ArrayList<Int>> = MutableLiveData()
    lateinit var context: Context
    fun init(context:Context){
        this.context = context
    }
    fun readTextFile(){
        val readTextFile = TextFileReader()
        readTextFile.readTextFile(App.filename, context)
        scoreList.value = readTextFile.arrayList
    }
    fun resetTextFile(){
        val textFileResetter = TextFileResetter()
        textFileResetter.resetTextFile(App.filename, context)
        readTextFile()
    }
    fun getScoreList():LiveData<ArrayList<Int>>{
        scoreList.value?.sortDescending()
        scoreList.postValue(scoreList.value)
        return scoreList
    }
}