package com.example.animalquizgame.viewmodels
import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.animalquizgame.models.QuizItem
import com.example.animalquizgame.repositories.QuizItemRepositories

class QuizItemViewModel : ViewModel() {
    private val mQuizItems: MutableLiveData<List<QuizItem>> = MutableLiveData()
    private lateinit var mRepo: QuizItemRepositories
    private val mIsUpdating: MutableLiveData<Boolean> = MutableLiveData()
    private val mIsFinished: MutableLiveData<Boolean> = MutableLiveData()
    private var myImage: MutableLiveData<String> = MutableLiveData()
    private var currentQuizName: String = ""
    private var quizOptions: MutableLiveData<Array<String>> = MutableLiveData(Array(4) { "" })
    private var current: Int = 0
    private var allQuizNames: ArrayList<String> = ArrayList()
    private var currentAnswers: Array<String>?= null
    private var hasAnswer: MutableLiveData<Boolean> = MutableLiveData()
    private var size = 0

    fun getKeyAnswers():Array<String>{
        resetAllQuizNames()
        return allQuizNames.toTypedArray()
    }
    fun setAnswer(s:String){
        if(current<size)
            currentAnswers?.set(current, s)
    }

    fun getHasAnswer(): LiveData<Boolean>{
        return hasAnswer
    }
    fun getMyImage(): LiveData<String> {
        return myImage
    }
    fun getIsFinished(): LiveData<Boolean> {
        return mIsFinished
    }
    fun getQuizOptions(): LiveData<Array<String>> {
        return quizOptions
    }
    fun increaseCount() {
        if(current<size)
            hasAnswer.value = currentAnswers?.getOrNull(current)?.isNotEmpty() ?: false

        if(hasAnswer.value == true){
            current++
            if (current < (mQuizItems.value?.size ?: 0)) {
                currentQuizName = mQuizItems.value?.get(current)?.name.toString()
                myImage.value = mQuizItems.value?.get(current)?.resourcePic

                removeElement()
                updateQuizOptions()
                currentAnswers?.set(current, "")
            } else {
                mIsFinished.value = true
            }
        }
    }
    fun init() {
        if (mQuizItems.value != null) {
            return
        }
        mRepo = QuizItemRepositories.getInstance()
        mQuizItems.value = mRepo.getQuizItems().value

        size = mQuizItems.value?.size ?: 0
        currentAnswers = Array(size){""}

        currentQuizName = mQuizItems.value?.get(current)?.name.toString()
        myImage.value = mQuizItems.value?.get(current)?.resourcePic
        removeElement()
        updateQuizOptions()
    }
    fun removeElement() {
        resetAllQuizNames()
        val currentList = allQuizNames
        if (currentList.contains(currentQuizName)) {
            currentList.remove(currentQuizName)
            currentList.shuffle()
            allQuizNames = currentList
        }
    }

    fun updateQuizOptions() {
        val currentArray = quizOptions.value ?: emptyArray()
        if (currentArray.isNotEmpty()) {
            val newArray = currentArray.copyOf()
            newArray[0] = currentQuizName
            newArray[1] = allQuizNames[0]
            newArray[2] = allQuizNames[1]
            newArray[3] = allQuizNames[2]
            newArray.shuffle()
            quizOptions.value = newArray
        }
    }

    fun resetAllQuizNames(){
        allQuizNames.clear()
        mQuizItems.value?.forEach { quizItem ->
            allQuizNames.add(quizItem.name)
        }
    }

    fun getCurrentAnswers(): Array<String>? {
        return currentAnswers
    }


}