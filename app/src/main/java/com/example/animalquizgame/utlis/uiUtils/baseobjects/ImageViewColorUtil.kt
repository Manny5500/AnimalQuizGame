package com.example.animalquizgame.utlis.uiUtils.baseobjects

import android.app.Activity
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.PorterDuff
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat

object ImageViewColorUtil {
    fun setDefaultImageViewColor(context: Context, colorResId: Int, colorResId2: Int) {
        val color = ContextCompat.getColor(context, colorResId)
        val color2 = ContextCompat.getColor(context, colorResId2)

        val rootView = (context as Activity).findViewById<View>(android.R.id.content)
        changeImageViewColors(rootView, color, color2)
    }
    fun changeImageViewColors(view: View, color: Int, color2: Int) {
        if (view is ViewGroup) {
            for (i in 0 until view.childCount) {
                changeImageViewColors(view.getChildAt(i), color, color2)
            }
        } else if (view is ImageView) {
            view.backgroundTintList = ColorStateList.valueOf(color)
            view.setColorFilter(color2, PorterDuff.Mode.SRC_ATOP)
        }
    }
}