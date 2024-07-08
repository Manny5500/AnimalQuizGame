package com.example.animalquizgame.utlis.uiUtils.anim

import android.view.View
import android.view.animation.Animation
import android.view.animation.TranslateAnimation
import kotlinx.coroutines.CompletableDeferred

class MoveUpward {
    companion object {
        suspend fun animate(v:View){
            val animation = TranslateAnimation(0f, 0f, 0f, -50f) // move up by 50 pixels
            animation.duration = 1000
            animation.fillAfter = true // keep the final position after animation

            val deferred = CompletableDeferred<Boolean>()
            animation.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationStart(animation: Animation) {
                    // Animation started
                }

                override fun onAnimationEnd(animation: Animation) {
                    deferred.complete(true)
                }

                override fun onAnimationRepeat(animation: Animation) {
                    // Animation repeated
                }
            })

            // Start animation
            v.startAnimation(animation)
            deferred.await()
        }
    }
}