package com.example.animalquizgame.utlis.uiUtils.textViews

import android.content.Context
import android.graphics.drawable.GradientDrawable
import androidx.core.content.ContextCompat
import com.example.animalquizgame.R

class ShapeMaker {


    companion object {
        var theme = 0
        lateinit var context: Context
        val strokeWidth = 8
        val radius = 4f
        fun setShape():GradientDrawable{
            if(theme==1){
               return createShape( R.color.themepinkvio, R.color.themepink)
            }else if(theme==2){
                return createShape(R.color.themegreenbg, R.color.themegreenhigh)
            }else if(theme==3){
               return createShape( R.color.themecontrolbg, R.color.themecontrolhigh)
            }else{
                return createShape( R.color.themeblack, R.color.themeblue)
            }
        }
        fun createShape(fillColor: Int, strokeColor: Int): GradientDrawable {
            return GradientDrawable().apply {
                shape = GradientDrawable.RECTANGLE
                cornerRadius = radius
                setColor(ContextCompat.getColor(context, fillColor))
                setStroke(strokeWidth, ContextCompat.getColor(context, strokeColor))
            }
        }

    }

}