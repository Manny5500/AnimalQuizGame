package com.example.animalquizgame.models
import com.example.animalquizgame.utlis.quizUtils.AnswerChecker
import com.example.animalquizgame.utlis.quizUtils.PhotoEquivalent
import com.example.animalquizgame.utlis.quizUtils.ScoreCalculator
import com.example.animalquizgame.utlis.quizUtils.StatusColor
import com.example.animalquizgame.utlis.quizUtils.StatusEvaluator

class QuizChecker(
    val keyAnswers: Array<String>,
    val playerAnswers: Array<String>,
    private val remainingTime: Int
) {
    var correctAnswers: Int = 0
    var status: String = ""
    var finalScore: Int = 0
    var imageUrl: String = ""
    var hexColor: String = ""

    init {
        evaluateQuiz()
    }

    private fun evaluateQuiz() {
        val answerChecker = AnswerChecker(playerAnswers, keyAnswers)
        correctAnswers = answerChecker.correctAnswers

        val scoreCalculator = ScoreCalculator(correctAnswers, remainingTime)
        finalScore = scoreCalculator.finalScore

        val statusEvaluator = StatusEvaluator()
        status = statusEvaluator.getStatus(correctAnswers, keyAnswers.size)

        val photoEquivalent = PhotoEquivalent()
        imageUrl = photoEquivalent.getImageUrl(status)

        val statusColor = StatusColor()
        hexColor = statusColor.getStatusColor(status)
    }
}
