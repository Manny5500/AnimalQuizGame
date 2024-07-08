package com.example.animalquizgame.utlis.uiUtils.anim

import android.os.Handler
import android.os.Looper
import android.widget.TextView
import kotlinx.coroutines.CompletableDeferred

class TextAnimation {
    companion object{
        suspend fun animate(v: TextView) {
            val handler = Handler(Looper.getMainLooper())
            val textToAnimate = "AniQuiz"
            var index = 0
            val deferred = CompletableDeferred<Boolean>()
            handler.postDelayed(object : Runnable {
                override fun run() {
                    if (index <= textToAnimate.length) {
                        v.text = textToAnimate.substring(0, index++)
                        handler.postDelayed(this, 200)
                    } else {
                        deferred.complete(true)
                    }
                }
            }, 500)
            deferred.await()
        }
    }
}