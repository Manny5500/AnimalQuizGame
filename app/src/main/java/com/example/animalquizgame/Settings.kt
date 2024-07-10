package com.example.animalquizgame


import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.animalquizgame.databinding.ActivitySettingsBinding
import com.example.animalquizgame.utlis.uiUtils.general.SetRootBackground
import com.example.animalquizgame.utlis.uiUtils.general.SettingsUIChanger
import com.example.animalquizgame.viewmodels.SettingsViewModel


class Settings : BaseActivity(){
    private lateinit var stVM:SettingsViewModel
    private lateinit var binding:ActivitySettingsBinding
    private lateinit var sharedPreferences:SharedPreferences
    private val settingUIChanger = SettingsUIChanger
    private var themeValue = 0
    private var time = 10
    private  var quiz = 10
    private var themeNow = "Theme1"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        stVM = ViewModelProvider(this)[SettingsViewModel::class.java]
        binding.viewModel = stVM
        binding.lifecycleOwner = this

        binding.btnSaveSettings.visibility = View.GONE

        setSharedPreferences()
        SetRootBackground.setbg(themeValue, this, binding.root)
        initializeSTVM()
        setSettingsUIChanger()
        observers()
        btnsEvent()
    }

    private fun initializeSTVM(){
        stVM.init(
            time, quiz, themeValue
        )
    }
    private fun observers(){
        stVM.getTimeCountText().observe(this){str->
            binding.textLtc.text = str
        }

        stVM._isTheSame.observe(this){bool->
            if(!bool){
                binding.btnSaveSettings.visibility = View.VISIBLE
            }else{
                binding.btnSaveSettings.visibility = View.GONE
            }
        }
        stVM.getThemeText().observe(this){str->
            themeNow = str
            SettingsUIChanger.setUI(str)
        }
    }

    private fun btnsEvent(){
        binding.btnSaveSettings.setOnClickListener{
            sharedPreferences.edit().putInt("time", stVM.timeCount).apply()
            sharedPreferences.edit().putInt("quiz", stVM.quizCount).apply()
            sharedPreferences.edit().putInt("theme", stVM.current).apply()
        }

        binding.btnResetSettings.setOnClickListener {
            sharedPreferences.edit().putInt("time", 10).apply()
            sharedPreferences.edit().putInt("quiz", 10).apply()
            sharedPreferences.edit().putInt("theme", 0).apply()
            setSharedPreferences()
            initializeSTVM()
            SettingsUIChanger.setUI("Theme 1")
        }
    }

    private fun setSettingsUIChanger(){
        settingUIChanger.activity = this
        settingUIChanger.context = this
        settingUIChanger.binding = binding
    }
    private fun setSharedPreferences(){
        sharedPreferences = getSharedPreferences("SETTINGS", Context.MODE_PRIVATE)
        themeValue = sharedPreferences.getInt("theme", 0)
        time = sharedPreferences.getInt("time", 10)
        quiz = sharedPreferences.getInt("quiz", 5)
    }

    override fun onResume() {
        super.onResume()
        //to ensure that the theme retain when sleep/unlock
        SettingsUIChanger.setUI(themeNow)
    }



}