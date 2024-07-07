package com.example.animalquizgame.utlis.uiUtils.textViews

import android.graphics.Color
import android.graphics.Typeface
import android.util.TypedValue
import android.widget.TextView

class CostumizeTextView {
    fun setTextSizeAndPaddingForTextViews(textSize:Float, padding:Int,
                                          typeface: Typeface, vararg textViews : TextView){
        for(tV in textViews){
            tV.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize)
            tV.setPadding(padding, padding, padding, padding)
            tV.typeface = typeface
            tV.setTextColor(Color.parseColor("#EEEEEE"))

        }
    }
}