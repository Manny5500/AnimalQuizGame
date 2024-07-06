package com.example.animalquizgame.models
import android.util.Log
import com.example.animalquizgame.utlis.quizUtils.AnswerChecker
import com.example.animalquizgame.utlis.quizUtils.ScoreCalculator
import com.example.animalquizgame.utlis.quizUtils.StatusEvaluator

class QuizChecker(
    val keyAnswers: Array<String>,
    val playerAnswers: Array<String>,
    private val remainingTime: Int,
    private val totalTime: Int
) {
    var correctAnswers: Int = 0
    var itemSize: Int = keyAnswers.size
    var status: String = ""
    var finalScore: Int = 0

    init {
        evaluateQuiz()
    }

    private fun evaluateQuiz() {
        val answerChecker = AnswerChecker(playerAnswers, keyAnswers)
        correctAnswers = answerChecker.correctAnswers

        Log.d("WAGGE", ""+correctAnswers)

        val scoreCalculator = ScoreCalculator(correctAnswers, remainingTime)
        finalScore = scoreCalculator.finalScore

        val statusEvaluator = StatusEvaluator()
        status = statusEvaluator.getStatus(correctAnswers, keyAnswers.size)
    }
}
