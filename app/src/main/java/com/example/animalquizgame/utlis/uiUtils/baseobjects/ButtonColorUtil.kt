package com.example.animalquizgame.utlis.uiUtils.baseobjects

import android.app.Activity
import android.content.Context
import android.content.res.ColorStateList
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.google.android.material.button.MaterialButton

object ButtonColorUtil {
    fun setDefaultButtonColor(context: Context, colorResId: Int, colorResId2: Int) {
        val color = ContextCompat.getColor(context, colorResId)
        var color2 = ContextCompat.getColor(context, colorResId2)
        val rootView = (context as Activity).findViewById<View>(android.R.id.content)
        changeButtonColors(rootView, color, color2)
    }
    fun changeButtonColors(view: View, color: Int, color2:Int) {
        if (view is ViewGroup) {
            for (i in 0 until view.childCount) {
                changeButtonColors(view.getChildAt(i), color, color2)
            }
        } else if (view is MaterialButton) {
            view.setBackgroundColor(color)
            view.strokeColor = ColorStateList.valueOf(color2)
        }
    }


}