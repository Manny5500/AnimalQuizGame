package com.example.animalquizgame.viewmodels

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TimerViewModel: ViewModel() {
    private val _timeRemaining = MutableLiveData<Long>()
    private val _isFinished = MutableLiveData<Boolean>()

    private lateinit var countDownTimer: CountDownTimer

    private val timeFormat = MutableLiveData<String>()

    private val _isRush = MutableLiveData<Boolean>()

    fun init(){
        startTimer(10000, 1000)
        _isRush.value = false
    }

    fun startTimer(duration: Long, interval: Long) {
        countDownTimer = object : CountDownTimer(duration, interval) {

            override fun onTick(millisUntilFinished: Long) {
                _timeRemaining.value = millisUntilFinished
                timeFormat.value = formatMillisToTime(_timeRemaining.value!!)

                if(_timeRemaining.value!!<6000){
                    _isRush.value = true;
                }
            }

            override fun onFinish() {
                _isFinished.value = true
            }
        }
        countDownTimer.start()
    }

    fun getIsRush():LiveData<Boolean>{
        return _isRush
    }
    fun getIsTimeFinished(): LiveData<Boolean> {
        return _isFinished
    }

    fun getTimeFormatted(): LiveData<String> {
        return timeFormat
    }

    private fun formatMillisToTime(millis: Long): String {
        val minutes = (millis / 1000) / 60
        val seconds = (millis / 1000) % 60
        return String.format("%02d:%02d", minutes, seconds)
    }

    fun stopTimer() {
        countDownTimer.cancel()
    }

    fun getRemainingTime(): Int {
        val rTimeInSec: Long = _timeRemaining.value?.div(1000) ?: 0
        return rTimeInSec.toInt()
    }

    fun getTotalTime():Int{
        return 10000/1000
    }
}