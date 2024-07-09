package com.example.animalquizgame


import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.animalquizgame.databinding.ActivityMainMenuBinding
import com.example.animalquizgame.utlis.uiUtils.anim.Alpha
import com.example.animalquizgame.utlis.uiUtils.anim.MoveUpward
import com.example.animalquizgame.utlis.uiUtils.anim.SpinAnimation
import com.example.animalquizgame.utlis.uiUtils.anim.TextAnimation
import com.example.animalquizgame.utlis.uiUtils.general.SetRootBackground
import com.example.animalquizgame.utlis.uiUtils.textViews.SpannableText
import com.example.animalquizgame.viewmodels.MainMenuViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainMenu : BaseActivity() {
    private lateinit var mVM:MainMenuViewModel
    private lateinit var binding:ActivityMainMenuBinding
    private var  spanColor:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPreferences = getSharedPreferences("SETTINGS", Context.MODE_PRIVATE)
        val theme = sharedPreferences.getInt("theme", 0)

        spanColor = SetRootBackground.setbgSpan(theme, this, binding.root)


        mVM = ViewModelProvider(this)[MainMenuViewModel::class.java]
        binding.viewModel = mVM
        binding.lifecycleOwner = this

        binding.btnNewGame.visibility = View.INVISIBLE
        binding.btnHighScores.visibility = View.INVISIBLE
        binding.imgViewSetting.visibility = View.INVISIBLE

        animationSequence()
        buttonsEvent()
    }

    private fun animationSequence() {

        lifecycleScope.launch {

            withContext(Dispatchers.Main){
                TextAnimation.animate(binding.tVTitle)
            }

            withContext(Dispatchers.Main){
                SpannableText.colorize(binding.tVTitle,0,
                    3 ,spanColor)
                Alpha.animate(binding.tVTitle)
                MoveUpward.animate(binding.tVTitle)
            }

            withContext(Dispatchers.Main){
                binding.btnNewGame.visibility = View.VISIBLE
                binding.btnHighScores.visibility = View.VISIBLE
                Alpha.animateReturned(binding.btnNewGame,
                    binding.btnHighScores)
            }

            withContext(Dispatchers.Main){
                binding.imgViewSetting.visibility = View.VISIBLE
                SpinAnimation.animate(binding.imgViewSetting)
            }

        }

    }
    private fun buttonsEvent(){
        binding.btnNewGame.setOnClickListener{
            navigateTo(MainActivity::class.java)
        }
        binding.btnHighScores.setOnClickListener {
            navigateTo(HighScore::class.java)
        }
        binding.imgViewSetting.setOnClickListener{
            navigateTo(Settings::class.java)
        }
    }
    private fun navigateTo(cls:Class<*>) {
        val intent = Intent(this, cls)
        startActivity(intent)
    }

}