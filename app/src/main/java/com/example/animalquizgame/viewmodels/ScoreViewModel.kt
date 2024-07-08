package com.example.animalquizgame.viewmodels

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.animalquizgame.App
import com.example.animalquizgame.models.QuizChecker
import com.example.animalquizgame.utlis.fileUtils.TextFileReader
import com.example.animalquizgame.utlis.fileUtils.TextFileWriter

class ScoreViewModel: ViewModel(){

    var status:String = ""
    var finalScore:Int = 0
    var correctAnswer:Int = 0
    lateinit var quizChecker: QuizChecker
    var imageUrl:String = ""
    var hexColor:String = ""
    lateinit var context: Context
    private val scoreList: MutableLiveData<ArrayList<Int>> = MutableLiveData()
    private val isHigher: MutableLiveData<Boolean> = MutableLiveData()
    fun init(remainingTime: Int, playerAnswers: Array<String>,
             keyAnswers: Array<String>){

        quizChecker = QuizChecker(keyAnswers, playerAnswers, remainingTime)

        this.status = quizChecker.status
        this.finalScore = quizChecker.finalScore
        this.correctAnswer = quizChecker.correctAnswers
        this.imageUrl = quizChecker.imageUrl
        this.hexColor = quizChecker.hexColor
    }

    fun readTextFile() : ScoreViewModel{
        val readTextFile = TextFileReader()
        readTextFile.readTextFile(App.filename, context)
        scoreList.value = readTextFile.arrayList
        return this
    }
    fun setScoreList() : ScoreViewModel{
        if(scoreList.value?.size!! < 1){
            scoreList.value?.add(0)
        }
        scoreList.value?.sortDescending()
        scoreList.postValue(scoreList.value)
        return this
    }
    fun compareScore() :  ScoreViewModel{
        if(finalScore> scoreList.value?.get(0)!!){
            isHigher.value = true
        }
        return this
    }
    fun getIsHigher():LiveData<Boolean>{
        return isHigher
    }
    fun writeTextFile(){
        val textFileWriter = TextFileWriter()
        textFileWriter.writeTextFile(App.filename, context, finalScore.toString())
    }


}