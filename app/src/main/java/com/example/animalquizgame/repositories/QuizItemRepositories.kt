package com.example.animalquizgame.repositories

import androidx.lifecycle.MutableLiveData
import com.example.animalquizgame.models.QuizItem

class QuizItemRepositories {
    companion object {
        private lateinit var instance: QuizItemRepositories

        fun getInstance(): QuizItemRepositories {
            if (!::instance.isInitialized) {
                instance = QuizItemRepositories()
            }
            return instance
        }
    }
    private val dataSet: ArrayList<QuizItem> = arrayListOf()

    fun getQuizItems(): MutableLiveData<List<QuizItem>> {
        setQuizItems()
        val data = MutableLiveData<List<QuizItem>>()
        data.value = dataSet
        return data
    }

    private fun setQuizItems() {
        dataSet.clear()
        dataSet.add(
            QuizItem("Pig",
            "https://firebasestorage.googleapis.com/v0/b/sample-6cfc8.appspot.com/o/images%2Fpig-7728182_1280.jpg?alt=media&token=478afc48-ebe5-49c9-973c-bffedd51d6bb"))
        dataSet.add(
            QuizItem("Dog",
            "https://firebasestorage.googleapis.com/v0/b/sample-6cfc8.appspot.com/o/images%2Fdog-8262506_1280.jpg?alt=media&token=de972e0f-e866-4456-80b5-0af7567917b4"))
        dataSet.add(
            QuizItem("Cat",
                "https://firebasestorage.googleapis.com/v0/b/sample-6cfc8.appspot.com/o/images%2Feuropean-shorthair-8601492_1280.jpg?alt=media&token=469afed5-17cd-45b5-aae1-3b1ed53fda21")
        )
        dataSet.add(
            QuizItem("Fish",
                "https://firebasestorage.googleapis.com/v0/b/sample-6cfc8.appspot.com/o/images%2Ffishes-7314666_1280.jpg?alt=media&token=4efa7ffd-dc39-4c61-9cf8-df2cb0a93c95")
        )
        dataSet.add(
            QuizItem("Bird",
                "https://firebasestorage.googleapis.com/v0/b/sample-6cfc8.appspot.com/o/images%2Fbird-4051412_1280.jpg?alt=media&token=eb3dec66-61c3-42de-8cf4-ed407f42d465")
        )
        dataSet.add(
            QuizItem("Sheep",
                "https://firebasestorage.googleapis.com/v0/b/sample-6cfc8.appspot.com/o/images%2Fsheep-7117465_1280.jpg?alt=media&token=1b92859a-222f-47c3-baf2-18f6ceb40e25")
        )
        dataSet.add(
            QuizItem("Cow",
                "https://firebasestorage.googleapis.com/v0/b/sample-6cfc8.appspot.com/o/images%2Fcow-425164_1280.jpg?alt=media&token=5999f693-a60d-457c-bd91-5c489121d355")
        )
        dataSet.add(
            QuizItem("Carabao",
                "https://firebasestorage.googleapis.com/v0/b/sample-6cfc8.appspot.com/o/images%2Fcarabao-5753656_1280.jpg?alt=media&token=12cb32e1-c5a4-42ac-991b-4d02b341bb82")
        )
        dataSet.shuffle()
    }

}