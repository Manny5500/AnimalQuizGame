package com.example.animalquizgame.utlis.uiUtils.button

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.example.animalquizgame.utlis.uiUtils.textViews.TitleTextView

object TitleTextViewUtil {
    fun setDefaultTextViewColor(context: Context, colorResId: Int) {
        val color = ContextCompat.getColor(context, colorResId)
        val rootView = (context as Activity).findViewById<View>(android.R.id.content)
        changeTextViewColors(rootView, color)
    }
    fun changeTextViewColors(view: View, color: Int) {
        if (view is ViewGroup) {
            for (i in 0 until view.childCount) {
                changeTextViewColors(view.getChildAt(i), color)
            }
        } else if (view is TitleTextView) {
            view.setTextColor(color)
        }
    }
}