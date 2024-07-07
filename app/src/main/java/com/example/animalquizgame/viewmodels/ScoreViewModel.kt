package com.example.animalquizgame.viewmodels

import androidx.lifecycle.ViewModel
import com.example.animalquizgame.models.QuizChecker
class ScoreViewModel: ViewModel(){

    var status:String = ""
    var finalScore:Int = 0
    var correctAnswer:Int = 0
    lateinit var quizChecker: QuizChecker
    var imageUrl:String = ""
    var hexColor:String = ""
    fun init(remainingTime: Int, playerAnswers: Array<String>,
             keyAnswers: Array<String>){

        quizChecker = QuizChecker(keyAnswers, playerAnswers, remainingTime)

        this.status = quizChecker.status
        this.finalScore = quizChecker.finalScore
        this.correctAnswer = quizChecker.correctAnswers
        this.imageUrl = quizChecker.imageUrl
        this.hexColor = quizChecker.hexColor
    }


}