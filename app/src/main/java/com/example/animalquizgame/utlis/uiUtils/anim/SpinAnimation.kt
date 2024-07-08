package com.example.animalquizgame.utlis.uiUtils.anim

import android.animation.ObjectAnimator
import android.view.View

class SpinAnimation {
    companion object {
        fun animate(v: View) {
            val animator = ObjectAnimator.ofFloat(v, "rotation", 0f, 360f)
            animator.duration = 1000
            animator.start()
        }
    }
}