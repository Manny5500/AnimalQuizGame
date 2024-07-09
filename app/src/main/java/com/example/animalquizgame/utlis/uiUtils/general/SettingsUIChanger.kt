package com.example.animalquizgame.utlis.uiUtils.general

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.PorterDuff
import android.os.Build
import androidx.core.content.ContextCompat
import com.example.animalquizgame.R
import com.example.animalquizgame.databinding.ActivitySettingsBinding

class SettingsUIChanger {

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var binding: ActivitySettingsBinding
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
        @SuppressLint("StaticFieldLeak")
        lateinit var activity: Activity
        fun setUI(str: String) {
            if(str=="Theme 1"){
                setNow(arrayOf(R.color.themeblack, R.color.themeblue, R.color.themewhite))
            }else if(str=="Theme 2"){
                setNow(arrayOf(R.color.themepinkvio, R.color.themepink, R.color.themepinkwhite))
            }else if(str=="Theme 3"){
                setNow( arrayOf(R.color.themegreenbg, R.color.themegreenhigh, R.color.themegreentxt))
            }else{
                setNow(arrayOf(R.color.themecontrolbg, R.color.themecontrolhigh, R.color.themecontroltext))
            }
        }

        fun setNow(array:Array<Int>){
            val btns = arrayOf(
                binding.btnSaveSettings,
                binding.btnResetSettings
            )
            for(b in btns){
                b.setTextColor(convert(array[2]))
                b.strokeColor = ColorStateList.valueOf(convert(array[1]))
                b.setBackgroundColor(convert(array[0]))
            }
            binding.btnSaveSettings.setTextColor(convert(array[2]))
            binding.btnResetSettings.setTextColor(convert(array[2]))
            binding.btnSaveSettings.strokeColor = ColorStateList.valueOf(convert(array[1]))
            binding.btnResetSettings.strokeColor = ColorStateList.valueOf(convert(array[1]))

            val imgViews = arrayOf(
                binding.btnLtcUp, binding.btnLtcDown,
                binding.btnLqcUp, binding.btnLqcDown,
                binding.btnLthcUp, binding.btnLthcDown
            )

            for(iv in imgViews){
                iv.backgroundTintList = ColorStateList.valueOf(convert(array[1]))
                iv.setColorFilter(convert(array[2]), PorterDuff.Mode.SRC_ATOP)
            }

            binding.tVSettings.setTextColor(convert(array[1]))

            val tViews = arrayOf(
                binding.textTime, binding.textQuestion, binding.textTheme,
                binding.textLtc, binding.textLqc, binding.textLthc
            )

            for(tv in tViews){
                tv.setTextColor(convert(array[2]))
            }

            binding.root.setBackgroundColor(convert(array[0]))

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                activity.window.statusBarColor = convert(array[1])
            }
        }

        fun convert(col:Int):Int{
            return ContextCompat.getColor(context, col)
        }
    }
}