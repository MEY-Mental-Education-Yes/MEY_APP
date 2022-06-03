package com.candra.projectcapstone.helper

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.view.View
import android.widget.ImageView
import com.google.android.material.button.MaterialButton
import com.google.android.material.textview.MaterialTextView

object Animation {

    fun playAnimationTempt(image: ImageView,btnLogin: MaterialButton,btnRegister: MaterialButton)
    {
        val animationImage =  ObjectAnimator.ofFloat(image, View.ALPHA,1f).setDuration(500)
        val animationBtnLogin = ObjectAnimator.ofFloat(btnLogin,View.ALPHA,1f).setDuration(500)
        val animationBtnRegister = ObjectAnimator.ofFloat(btnRegister,View.ALPHA,1f).setDuration(500)

        AnimatorSet().apply {
            playSequentially(animationImage,animationBtnLogin,animationBtnRegister)
            start()
        }
    }

    fun playAnimationSplash(image: ImageView,title: MaterialTextView){
        val animationImage = ObjectAnimator.ofFloat(image,View.ALPHA,1f).setDuration(500)
        val animationTitle = ObjectAnimator.ofFloat(title,View.ALPHA,1f).setDuration(500)

        AnimatorSet().apply {
            playSequentially(animationImage,animationTitle)
            start()
        }
    }

}