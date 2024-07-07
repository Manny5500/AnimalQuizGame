package com.example.animalquizgame.utlis.quizUtils

class ScoreCalculator (
    val correctAnswers: Int,
    val remainingTime: Int
) {
    var finalScore: Int = 0
    init {
        setFinalScore()
    }
    fun setFinalScore(){
        val a = correctAnswers * 200
        val b = if (a > 0) remainingTime * 5 else 0
        finalScore =  a+b
    }
}