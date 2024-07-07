package com.example.animalquizgame

import android.animation.ObjectAnimator
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.View
import android.view.animation.Animation
import android.view.animation.TranslateAnimation
import androidx.lifecycle.ViewModelProvider
import com.example.animalquizgame.databinding.ActivityMainMenuBinding
import com.example.animalquizgame.viewmodels.MainMenuViewModel

class MainMenu : AppCompatActivity() {
    lateinit var mVM:MainMenuViewModel
    lateinit var binding:ActivityMainMenuBinding
    lateinit var handler:Handler
    lateinit var textToAnimate:String
    var index:Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mVM = ViewModelProvider(this)[MainMenuViewModel::class.java]
        binding.viewModel = mVM
        binding.lifecycleOwner = this

        binding.btnNewGame.visibility = View.INVISIBLE
        binding.btnHighScores.visibility = View.INVISIBLE

        animateText()
        buttonsEvent()
    }

    private fun animateText() {
        handler = Handler(Looper.getMainLooper())
        textToAnimate = "AniQuiz"
        handler.postDelayed(object : Runnable {
            override fun run() {
                if (index <= textToAnimate.length) {
                    binding.tVTitle.text = textToAnimate.substring(0, index++)
                    handler.postDelayed(this, 200)
                } else {
                    setTextViewColor()
                    animate(binding.tVTitle)
                    moveUpward()
                }
            }
        }, 500)

    }

    private fun setTextViewColor() {
        val text = "AniQuiz"
        val spannableString = SpannableString(text)
        spannableString.setSpan(
            ForegroundColorSpan(Color.parseColor("#00ADB5")),
            0, 3, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        binding.tVTitle.text = spannableString
    }


    private fun moveUpward() {
        val animation = TranslateAnimation(0f, 0f, 0f, -50f) // move up by 50 pixels
        animation.duration = 1000
        animation.fillAfter = true // keep the final position after animation

        // Set animation listener if needed
        animation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {
                // Animation started
            }
            override fun onAnimationEnd(animation: Animation) {
                binding.btnNewGame.visibility = View.VISIBLE
                binding.btnHighScores.visibility = View.VISIBLE
                animate(binding.btnNewGame, binding.btnHighScores)
            }
            override fun onAnimationRepeat(animation: Animation) {
                // Animation repeated
            }
        })
        // Start animation
        binding.tVTitle.startAnimation(animation)
    }

    private fun animate(vararg views: View) {
        for (v in views) {
            val animator = ObjectAnimator.ofFloat(v, "alpha", 0f, 1f)
            animator.duration = 1000
            animator.start()
        }
    }

    private fun buttonsEvent(){
        binding.btnNewGame.setOnClickListener{
            navigateTo(MainActivity::class.java)
        }
        binding.btnHighScores.setOnClickListener {
            navigateTo(HighScore::class.java)
        }
    }
    private fun navigateTo(cls:Class<*>) {
        val intent = Intent(this, cls)
        startActivity(intent)
    }


}