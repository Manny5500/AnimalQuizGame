package com.example.animalquizgame.utlis.quizUtils

class PhotoEquivalent {
    fun getImageUrl(status:String): String {
        return when (status) {
            "Perfect" -> "https://firebasestorage.googleapis.com/v0/b/sample-6cfc8.appspot.com/o/images%2FpCat.jpg?alt=media&token=9f3983dc-2375-4c1a-9f92-48b411760111"
            "Great" -> "https://firebasestorage.googleapis.com/v0/b/sample-6cfc8.appspot.com/o/images%2FhappyDog.jpg?alt=media&token=5b088aa2-1057-439c-a22a-1e3e89bf1b35"
            "Good" -> "https://firebasestorage.googleapis.com/v0/b/sample-6cfc8.appspot.com/o/images%2FgCat.jpg?alt=media&token=e74e3510-a96f-4ec3-8261-23a743e5d360"
            "Not Bad" -> "https://firebasestorage.googleapis.com/v0/b/sample-6cfc8.appspot.com/o/images%2FnbDog.jpg?alt=media&token=81712a8c-03c5-4691-a434-65a4873be6cf"
            else -> "https://firebasestorage.googleapis.com/v0/b/sample-6cfc8.appspot.com/o/images%2Fsadcat.png?alt=media&token=d7d511d5-924b-47b6-aa54-841ff3e9f5f3"
        }
    }
}