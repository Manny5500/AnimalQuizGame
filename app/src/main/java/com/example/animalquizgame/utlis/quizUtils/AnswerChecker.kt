package com.example.animalquizgame.utlis.quizUtils

class AnswerChecker (
    val playerAnswers:Array<String>,
    val keyAnswers: Array<String>
){
    var correctAnswers:Int = 0


    init{
        setCorrectAnswers()
    }
    fun setCorrectAnswers(){
        for(i in playerAnswers.indices){
            if(playerAnswers[i] == keyAnswers[i]){
                correctAnswers++
            }
        }
    }

}