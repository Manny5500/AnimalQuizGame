package com.example.animalquizgame.utlis.quizUtils

class StatusColor {
    fun getStatusColor(status: String): String{
        return when (status) {
            "Perfect" -> "#f65154"
            "Great" ->"#8b519a"
            "Good" -> "#f5809e"
            "Not Bad"  -> "#00acb4"
            else ->"#d34242"
        }

    }
}