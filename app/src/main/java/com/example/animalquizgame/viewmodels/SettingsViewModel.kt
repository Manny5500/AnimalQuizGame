package com.example.animalquizgame.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SettingsViewModel: ViewModel() {
    var timeCount:Int = 10
    var quizCount:Int = 10


    var _timeCount = MutableLiveData(timeCount.toString())

    var _quizCount = MutableLiveData(quizCount.toString())
    var _quizCountText:LiveData<String> = _quizCount


    val theme_Array = arrayOf("Theme 1", "Theme 2", "Theme 3", "Theme 4")
    var current = 0
    var currentText = MutableLiveData(theme_Array[current])
    var _currentText:LiveData<String> = currentText

    var isTheSame = MutableLiveData(true)
    var _isTheSame:LiveData<Boolean> = isTheSame



    var origTime = 0
    var origQuiz = 0
    var origCurrent = 0

    fun init(timeCount:Int, quizCount:Int, current:Int){
        this.timeCount = timeCount
        this.quizCount = quizCount
        this.current = current

        origTime = timeCount
        origQuiz = quizCount
        origCurrent = current

        _timeCount.value = timeCount.toString()
        _quizCount.value = quizCount.toString()
        currentText.value = theme_Array[current]
    }

    fun getTimeCountText():LiveData<String>{
        return _timeCount
    }

    fun getThemeText():LiveData<String>{
        return _currentText
    }

    fun evaluateIsTheSame(){
        isTheSame.value = !(timeCount!= origTime || quizCount!= origQuiz || current!= origCurrent)
    }
    fun increaseTimeCount(){
        if(timeCount<60){
            timeCount+=10
        }
        _timeCount.value = timeCount.toString()
        evaluateIsTheSame()
    }
    fun increaseQuizCount(){
        if(quizCount<20){
            quizCount+=5
        }
        _quizCount.value = quizCount.toString()
        evaluateIsTheSame()
    }

    fun decreaseTimeCount(){
        if(timeCount>10){
            timeCount-=10
        }
        _timeCount.value = timeCount.toString()
        evaluateIsTheSame()
    }

    fun decreaseQuizCount(){
        if(quizCount>5){
            quizCount-=5
        }
        _quizCount.value = quizCount.toString()
        evaluateIsTheSame()
    }

    fun backTheme(){
        if(current>0){
            current--
        }
        currentText.value = theme_Array[current]
        evaluateIsTheSame()
    }

    fun nextTheme(){
        if(current<3){
            current++
        }
        currentText.value = theme_Array[current]
        evaluateIsTheSame()
    }


}