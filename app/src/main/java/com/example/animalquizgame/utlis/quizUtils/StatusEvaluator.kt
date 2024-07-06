package com.example.animalquizgame.utlis.quizUtils

class StatusEvaluator {
    fun getStatus(correctAnswers: Int, itemSize: Int):String {
        val percentage:Double =  correctAnswers.toDouble()/itemSize * 100
        return when{
            percentage == 100.0 -> "Perfect"
            percentage < 100 && percentage >=95 -> "Great"
            percentage < 95 && percentage >=85 -> "Good"
            percentage < 85 && percentage >=50 -> "Not Bad"
            else -> "Sorry"
        }
    }

}