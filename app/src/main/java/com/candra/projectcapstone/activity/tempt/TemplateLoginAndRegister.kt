package com.candra.projectcapstone.activity.tempt

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.candra.projectcapstone.activity.login.LoginActivity
import com.candra.projectcapstone.activity.register.RegisterActivity
import com.candra.projectcapstone.databinding.ActivityTemptBinding
import com.candra.projectcapstone.helper.Animation
import com.candra.projectcapstone.helper.Helper

class TemplateLoginAndRegister: AppCompatActivity()
{
    private lateinit var binding: ActivityTemptBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTemptBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            Animation.playAnimationTempt(image = logoApp,btnLogin = btnLoginTempt,btnRegister = btnRegisterTempt)
            btnLoginTempt.setOnClickListener {
                Helper.toNavigateLoginActivity(this@TemplateLoginAndRegister)
                finish()
            }
            btnRegisterTempt.setOnClickListener {
               Helper.toNavigateRegisterActivity(this@TemplateLoginAndRegister)
                finish()
            }
        }
    }
}