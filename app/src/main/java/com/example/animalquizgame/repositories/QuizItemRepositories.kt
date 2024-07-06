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
            "https://static.vecteezy.com/system/resources/thumbnails/007/528/158/small_2x/pig-cartoon-colored-clipart-illustration-free-vector.jpg"))
        dataSet.add(
            QuizItem("Dog",
            "https://img.freepik.com/free-vector/beagle-dog-cartoon-white-background_1308-68249.jpg"))
        dataSet.add(
            QuizItem("Cat",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQUqysRzNArCWsHFa5In0BANe3wg7kaQldxbQ&s")
        )
        dataSet.add(
            QuizItem("Fish",
                "https://st.depositphotos.com/1526816/2883/v/450/depositphotos_28832495-stock-illustration-a-nemo-fish.jpg")
        )
        dataSet.add(
            QuizItem("Bird",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/4/45/Eopsaltria_australis_-_Mogo_Campground.jpg/640px-Eopsaltria_australis_-_Mogo_Campground.jpg")
        )
        dataSet.add(
            QuizItem("Sheep",
                "https://i.pinimg.com/736x/93/bd/88/93bd88568261510ce89a77248914b84d.jpg")
        )
        dataSet.add(
            QuizItem("Cow",
                "https://upload.wikimedia.org/wikipedia/commons/0/0c/Cow_female_black_white.jpg")
        )
        dataSet.add(
            QuizItem("Carabao",
                "https://upload.wikimedia.org/wikipedia/commons/9/90/Guiguinto_Municipality_in_Bulacan_08.jpg")
        )
        dataSet.shuffle()
    }

}