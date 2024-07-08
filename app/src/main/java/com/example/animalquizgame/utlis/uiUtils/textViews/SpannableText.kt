package com.example.animalquizgame.utlis.uiUtils.textViews

import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.widget.TextView

class SpannableText {

    companion object {
        fun colorize(v: TextView, start: Int, end:Int, color: Int) {
            val text = "AniQuiz"
            val spannableString = SpannableString(text)
            spannableString.setSpan(
                ForegroundColorSpan(color),
                start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            v.text= spannableString
        }
    }
}