package com.example.animalquizgame.utlis.uiUtils.general

import android.content.Context
import android.view.View
import androidx.core.content.ContextCompat
import com.example.animalquizgame.R

class SetRootBackground {

    companion object {
        fun setbg(theme:Int, context: Context, v: View){

            val bgColor: Int = when (theme) {
                1 -> {
                    R.color.themepinkvio
                }
                2 -> {
                    R.color.themegreenbg
                }
                3 -> {
                    R.color.themecontrolbg
                }
                else -> {
                    R.color.themeblack
                }
            }

            v.setBackgroundColor(ContextCompat.getColor(context, bgColor))
        }

        fun setbgSpan(theme:Int, context: Context, v: View):Int{
            val bgColor: Int
            val highColor: Int

            when (theme) {
                1 -> {
                    bgColor = R.color.themepinkvio
                    highColor = R.color.themepink
                }
                2 -> {
                    bgColor = R.color.themegreenbg
                    highColor = R.color.themegreenhigh
                }
                3 -> {
                    bgColor = R.color.themecontrolbg
                    highColor = R.color.themecontrolhigh
                }
                else -> {
                    bgColor = R.color.themeblack
                    highColor = R.color.themeblue
                }
            }

            v.setBackgroundColor(ContextCompat.getColor(context, bgColor))
            return ContextCompat.getColor(context, highColor)
        }
    }
}