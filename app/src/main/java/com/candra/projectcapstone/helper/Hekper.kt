package com.candra.projectcapstone.helper

import android.content.Context
import android.content.Intent
import android.widget.ImageView
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LifecycleCoroutineScope
import coil.load
import com.candra.projectcapstone.R
import com.candra.projectcapstone.activity.login.LoginActivity
import com.candra.projectcapstone.activity.register.RegisterActivity
import com.candra.projectcapstone.activity.tempt.TemplateLoginAndRegister
import kotlinx.coroutines.launch


object Helper {

    const val IMAGE_SCREEN_ONE = "https://raw.githubusercontent.com/MEY-Mental-Education-Yes/The-Logo-MEY/main/onBoarding_1.png"
    const val IMAGE_SCREEN_TWO = "https://raw.githubusercontent.com/MEY-Mental-Education-Yes/The-Logo-MEY/main/OnBoarding_2.png"
    const val IMAGE_SCREEN_THREE = "https://raw.githubusercontent.com/MEY-Mental-Education-Yes/The-Logo-MEY/main/onBoarding_3.png"


    fun imageScreen(image: ImageView,url: String){
        image.load(url){
            crossfade(true)
            crossfade(600)
            error(R.drawable.ic_baseline_broken_image_24)
        }
    }

    fun toScreenHome(fragmentActivity: FragmentActivity){
        fragmentActivity.startActivity(Intent(fragmentActivity,TemplateLoginAndRegister::class.java))
        fragmentActivity.finish()
    }

    fun setCheckedData(lifecycle: LifecycleCoroutineScope,context: Context){
        lifecycle.launch {
            LocalShared.setCheckedLocalOnBoarding(context)
        }
    }

    fun toNavigateLoginActivity(context: Context){
        context.startActivity(Intent(context,LoginActivity::class.java))
    }

    fun toNavigateRegisterActivity(context: Context){
        context.startActivity(Intent(context,RegisterActivity::class.java))
    }

}