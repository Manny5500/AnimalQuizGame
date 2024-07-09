package com.example.animalquizgame


import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.example.animalquizgame.databinding.ActivitySettingsBinding
import com.example.animalquizgame.utlis.uiUtils.general.SetRootBackground
import com.example.animalquizgame.utlis.uiUtils.general.SettingsUIChanger
import com.example.animalquizgame.viewmodels.SettingsViewModel

class Settings : BaseActivity(){
    private lateinit var stVM:SettingsViewModel
    private lateinit var binding:ActivitySettingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        stVM = ViewModelProvider(this)[SettingsViewModel::class.java]
        binding.viewModel = stVM
        binding.lifecycleOwner = this


        val sharedPreferences = getSharedPreferences("SETTINGS", Context.MODE_PRIVATE)
        val theme = sharedPreferences.getInt("theme", 0)
        SetRootBackground.setbg(theme, this, binding.root)


        stVM.init(
            sharedPreferences.getInt("time", 1),
            sharedPreferences.getInt("quiz", 1),
            theme
        )

        binding.btnSaveSettings.visibility = View.GONE

        stVM.getTimeCountText().observe(this){str->
            binding.textLtc.text = str
        }

        SettingsUIChanger.activity = this
        SettingsUIChanger.context = this
        SettingsUIChanger.binding = binding
        stVM.getThemeText().observe(this){str->
            SettingsUIChanger.setUI(str)
        }

        binding.btnSaveSettings.setOnClickListener{
            val sharedPreferences = getSharedPreferences("SETTINGS", Context.MODE_PRIVATE)
            sharedPreferences.edit().putInt("time", stVM.timeCount).apply()
            sharedPreferences.edit().putInt("quiz", stVM.quizCount).apply()
            sharedPreferences.edit().putInt("theme", stVM.current).apply()
            restartApp()
        }

        stVM._isTheSame.observe(this){bool->
            if(!bool){
                binding.btnSaveSettings.visibility = View.VISIBLE
            }else{
                binding.btnSaveSettings.visibility = View.GONE
            }
        }
    }

    private fun restartApp() {
        val intent = Intent(applicationContext, MainMenu::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(intent)
        finish()
    }
}