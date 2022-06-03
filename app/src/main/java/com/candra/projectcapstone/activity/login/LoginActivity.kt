package com.candra.projectcapstone.activity.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.candra.projectcapstone.databinding.ActivityLoginBinding
import com.candra.projectcapstone.helper.Helper

class LoginActivity: AppCompatActivity()
{
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.apply {
            textviewNotHaveAccount.setOnClickListener {
                Helper.toNavigateRegisterActivity(this@LoginActivity)
                finish()
            }
        }
    }
}