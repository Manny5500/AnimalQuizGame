package com.example.animalquizgame.utlis.uiUtils.buttons

import android.content.Context
import androidx.core.content.ContextCompat
import com.example.animalquizgame.R

class ButtonEventColorizer {
    companion object {
        fun getClickedColor(theme:Int, context: Context): Int {
            if(theme == 1){
                return  ContextCompat.getColor(context, R.color.themepink)
            }else if(theme ==2){
                return  ContextCompat.getColor(context, R.color.themegreenhigh)
            }else if(theme==3){
                return  ContextCompat.getColor(context, R.color.themecontrolhigh)
            }
            return ContextCompat.getColor(context, R.color.themeblue)

        }

        fun getUnclickedColor(theme: Int, context:Context): Int {
            if(theme == 1){
                return  ContextCompat.getColor(context, R.color.themepinkvio)
            }else if(theme ==2){
                return  ContextCompat.getColor(context, R.color.themegreenbg)
            }else if(theme==3){
                return  ContextCompat.getColor(context, R.color.themecontrolbg)
            }
            return ContextCompat.getColor(context, R.color.themeblack)
        }
    }

}