package com.example.animalquizgame.utlis.uiUtils.textViews

import android.content.Context
import androidx.core.content.ContextCompat
import com.example.animalquizgame.R

class SetThemeColorText {
    companion object {
        fun setColor(theme: Int, context: Context): Int {
            val textColor:Int = when (theme) {
                1 -> {
                    R.color.themepinkwhite
                }
                2 -> {
                    R.color.themegreentxt
                }
                3 -> {
                    R.color.themecontroltext
                }
                else -> {
                    R.color.themewhite
                }
            }

            return ContextCompat.getColor(context, textColor)
        }
    }
}