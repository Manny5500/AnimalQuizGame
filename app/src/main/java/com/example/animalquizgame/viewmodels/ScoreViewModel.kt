package com.example.animalquizgame.viewmodels

import androidx.lifecycle.ViewModel
import com.example.animalquizgame.models.QuizChecker
class ScoreViewModel: ViewModel(){

    var status:String = ""
    var finalScore:Int = 0
    var correctAnswer:Int = 0
    lateinit var quizChecker: QuizChecker
    fun init(remainingTime: Int, playerAnswers: Array<String>,
             keyAnswers: Array<String>, totalTime: Int){

        quizChecker = QuizChecker(keyAnswers, playerAnswers, remainingTime, totalTime)

        this.status = quizChecker.status
        this.finalScore = quizChecker.finalScore
        this.correctAnswer = quizChecker.correctAnswers
    }


}