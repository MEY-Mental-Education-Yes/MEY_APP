package com.candra.projectcapstone.helper

import android.content.Context
import android.content.Intent
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.LifecycleCoroutineScope
import coil.load
import com.candra.projectcapstone.R
import com.candra.projectcapstone.activity.home.MainActivity
import kotlinx.coroutines.launch


object Helper {

    const val IMAGE_SCREEN_ONE =
        "https://raw.githubusercontent.com/MEY-Mental-Education-Yes/The-Logo-MEY/main/onBoarding_1.png"
    const val IMAGE_SCREEN_TWO =
        "https://raw.githubusercontent.com/MEY-Mental-Education-Yes/The-Logo-MEY/main/OnBoarding_2.png"

    fun imageScreen(image: ImageView, url: String) {
        image.load(url) {
            crossfade(true)
            crossfade(600)
            error(R.drawable.ic_baseline_broken_image_24)
        }
    }

    fun toHomeScreen(fragmentActivity: FragmentActivity){
        fragmentActivity.startActivity(Intent(fragmentActivity,MainActivity::class.java))
        fragmentActivity.finish()
    }
    
    fun setCheckedData(lifecycle: LifecycleCoroutineScope, context: Context) {
        lifecycle.launch {
            LocalShared.setCheckedLocalOnBoarding(context)
        }
    }

    fun loadFragment(fragment: Fragment, fragmentManager: FragmentManager) {
        fragmentManager.beginTransaction().replace(R.id.parentContainer, fragment).commit()
    }

    fun makeToast(context: Context){
        Toast.makeText(context,context.getString(R.string.tahap_developer),Toast.LENGTH_SHORT).show()
    }


}
