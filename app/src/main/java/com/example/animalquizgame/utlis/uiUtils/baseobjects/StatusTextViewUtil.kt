package com.example.animalquizgame.utlis.uiUtils.baseobjects

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.example.animalquizgame.utlis.uiUtils.textViews.StatusTextView

object StatusTextViewUtil {
    fun setDefaultTextViewColor(context: Context, colorResId: Int, shadowColorResId: Int) {
        val color = ContextCompat.getColor(context, colorResId)
        val shadowColor = ContextCompat.getColor(context, shadowColorResId)
        val rootView = (context as Activity).findViewById<View>(android.R.id.content)
        changeTextViewColors(rootView, color, shadowColor)
    }
    fun changeTextViewColors(view: View, color: Int, shadowColor: Int) {
        if (view is ViewGroup) {
            for (i in 0 until view.childCount) {
                changeTextViewColors(view.getChildAt(i), color, shadowColor)
            }
        } else if (view is StatusTextView) {
            view.setTextColor(color)
            view.setShadowLayer(1F, 12F,
                5F, shadowColor)
        }
    }
}