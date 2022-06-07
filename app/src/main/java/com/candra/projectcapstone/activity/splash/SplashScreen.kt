package com.candra.projectcapstone.activity.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.candra.projectcapstone.activity.home.MainActivity
import com.candra.projectcapstone.activity.onboarding.ViewPagerActivity
import com.candra.projectcapstone.databinding.SplashScreenActivityBinding
import com.candra.projectcapstone.helper.Animation
import com.candra.projectcapstone.helper.LocalShared
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@SuppressLint("CustomSplashScreen")
class SplashScreen: AppCompatActivity()
{

    private lateinit var binding: SplashScreenActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SplashScreenActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        toAnotherActivity()
        Animation.playAnimationSplash(image = binding.logoApp,title = binding.title)
    }

    private fun toAnotherActivity(){
        Handler(mainLooper).postDelayed({
            lifecycleScope.launch {
               LocalShared.getCheckedLocalOnBoarding(this@SplashScreen).collect {
                   val target = if (it) MainActivity::class.java else ViewPagerActivity::class.java
                   startActivity(Intent(this@SplashScreen,target))
                   finish()
               }
            }
        },3000L)
    }



}