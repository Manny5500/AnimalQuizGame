package com.example.animalquizgame.utlis.uiUtils.baseobjects

import android.app.Activity
import android.content.Context
import android.content.res.ColorStateList
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.google.android.material.imageview.ShapeableImageView

object ShapableImageViewUtil {
    fun setDefaultImageViewColor(context: Context, colorResId: Int) {
        val color = ContextCompat.getColor(context, colorResId)

        val rootView = (context as Activity).findViewById<View>(android.R.id.content)
        changeImageViewColors(rootView, color)
    }
    fun changeImageViewColors(view: View, color: Int) {
        if (view is ViewGroup) {
            for (i in 0 until view.childCount) {
                changeImageViewColors(view.getChildAt(i), color)
            }
        } else if (view is ShapeableImageView) {
            view.strokeColor = ColorStateList.valueOf(color)
            view.clearColorFilter()
        }
    }
}