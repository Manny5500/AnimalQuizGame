package com.example.animalquizgame.utlis.uiUtils.general

import android.content.Context
import android.view.View
import androidx.core.content.ContextCompat
import com.example.animalquizgame.R

class SetRootBackground {

    companion object {
        fun setbg(theme:Int, context: Context, v: View){
            if(theme == 1){
                v.setBackgroundColor(ContextCompat.getColor(context, R.color.themepinkvio))
            }else if(theme ==2){
                v.setBackgroundColor(ContextCompat.getColor(context, R.color.themegreenbg))
            }else if(theme==3){
                v.setBackgroundColor(ContextCompat.getColor(context, R.color.themecontrolbg))
            }
        }

        fun setbgSpan(theme:Int, context: Context, v: View):Int{
            if(theme == 1){
                v.setBackgroundColor(ContextCompat.getColor(context, R.color.themepinkvio))
                return  ContextCompat.getColor(context, R.color.themepink)
            }else if(theme ==2){
                v.setBackgroundColor(ContextCompat.getColor(context, R.color.themegreenbg))
                return  ContextCompat.getColor(context, R.color.themegreenhigh)
            }else if(theme==3){
                v.setBackgroundColor(ContextCompat.getColor(context, R.color.themecontrolbg))
                return  ContextCompat.getColor(context, R.color.themecontrolhigh)
            }
            return ContextCompat.getColor(context, R.color.themeblue)
        }
    }
}