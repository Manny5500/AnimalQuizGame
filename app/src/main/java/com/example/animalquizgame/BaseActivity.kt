package com.example.animalquizgame

import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import com.example.animalquizgame.utlis.uiUtils.button.ButtonColorUtil
import com.example.animalquizgame.utlis.uiUtils.button.CircularImageViewUtil
import com.example.animalquizgame.utlis.uiUtils.button.HighlightedTextViewUtil
import com.example.animalquizgame.utlis.uiUtils.button.ImageViewColorUtil
import com.example.animalquizgame.utlis.uiUtils.button.ShapableImageViewUtil
import com.example.animalquizgame.utlis.uiUtils.button.TextViewColorUtil
import com.example.animalquizgame.utlis.uiUtils.button.TitleTextViewUtil
import com.google.android.material.imageview.ShapeableImageView

open class BaseActivity : AppCompatActivity() {
    override fun onStart() {
        super.onStart()
        val rootView: View = findViewById(android.R.id.content)
        val sharedPreferences = getSharedPreferences("SETTINGS", Context.MODE_PRIVATE)
        var theme = sharedPreferences.getInt("theme", 0)
        if(theme==0){
            setUIColors(R.color.themeblack, R.color.themeblue, R.color.themewhite)
        } else if(theme==1){
            setUIColors(R.color.themepinkvio, R.color.themepink, R.color.themepinkwhite)
        }else if(theme==2){
            setUIColors(R.color.themegreenbg, R.color.themegreenhigh, R.color.themegreentxt)
        }else if(theme==3){
            setUIColors(R.color.themecontrolbg, R.color.themecontrolhigh, R.color.themecontroltext)
        }else{

        }
    }
    fun setUIColors(bgColor:Int, highColor:Int, txtColor:Int){
        ButtonColorUtil.setDefaultButtonColor(this, bgColor, highColor)
        TextViewColorUtil.setDefaultTextViewColor(this, txtColor)
        ImageViewColorUtil.setDefaultImageViewColor(this, highColor, txtColor)
        TitleTextViewUtil.setDefaultTextViewColor(this, highColor)
        ShapableImageViewUtil.setDefaultImageViewColor(this, highColor)
        CircularImageViewUtil.setDefaultImageViewColor(this, highColor)
        HighlightedTextViewUtil.setDefaultTextViewColor(this, highColor)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = ContextCompat.getColor(this, highColor)
        }
    }


}