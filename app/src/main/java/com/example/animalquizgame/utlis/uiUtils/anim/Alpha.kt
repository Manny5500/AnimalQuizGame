package com.example.animalquizgame.utlis.uiUtils.anim

import android.animation.Animator
import android.animation.ObjectAnimator
import android.view.View
import kotlinx.coroutines.CompletableDeferred

class Alpha {
    companion object {
     fun animate(vararg views: View){
            for (v in views) {
                val animator = ObjectAnimator.ofFloat(v, "alpha", 0f, 1f)
                animator.duration = 1000
                animator.start()
            }
        }

        suspend fun animateReturned(vararg views :View){
            var countDown = views.size

            val deferred = CompletableDeferred<Boolean>()
            views.forEach { v ->
                val animator = ObjectAnimator.ofFloat(v, "alpha", 0f, 1f)
                animator.duration = 1000
                animator.addListener(object : Animator.AnimatorListener {
                    override fun onAnimationStart(animation: Animator) {
                    }

                    override fun onAnimationEnd(animation: Animator) {
                        countDown-- // Decrease the counter on animation end
                        if (countDown == 0) {
                            deferred.complete(true)
                        }
                    }

                    override fun onAnimationCancel(animation: Animator) {
                    }

                    override fun onAnimationRepeat(animation: Animator) {
                    }
                })
                animator.start()
            }
           deferred.await()
        }

    }
}