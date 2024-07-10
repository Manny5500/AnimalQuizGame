package com.example.animalquizgame.utlis.uiUtils.general

import android.content.Context
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.animalquizgame.R
import com.google.android.material.snackbar.Snackbar

class SetSnackBar {
    companion object {
        fun set(theme:Int, context: Context, v: View, message:String) {
            val textColor: Int
            val highColor: Int
            when (theme) {
                1 -> {
                    textColor = R.color.themepinkvio
                    highColor = R.color.themepinklite
                }
                2 -> {
                    textColor = R.color.themewhite
                    highColor = R.color.themegreenhigh
                }
                3 -> {
                    textColor = R.color.themecontrolhigh
                    highColor = R.color.themecontrollite
                }
                else -> {
                    textColor = R.color.themewhite
                    highColor = R.color.themeblue
                }
            }

            val snackbar = Snackbar.make(v, message, Snackbar.LENGTH_SHORT)
            val snackbarView = snackbar.view
            snackbarView.setBackgroundColor(ContextCompat.getColor(context,  highColor))
            val textView = snackbarView.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)
            textView.setTextColor(ContextCompat.getColor(context, textColor))
            textView.textSize = 18f
            snackbar.show()
        }
    }
}