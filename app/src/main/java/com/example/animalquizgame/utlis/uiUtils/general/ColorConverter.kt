package com.example.animalquizgame.utlis.uiUtils.general

import android.content.Context
import androidx.core.content.ContextCompat

class ColorConverter {
    companion object {


        lateinit var context: Context
        fun convert(colorId: Int):Int {
            return ContextCompat.getColor(context, colorId)
        }


    }
}