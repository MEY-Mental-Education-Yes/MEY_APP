package com.candra.projectcapstone.activity.register

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.candra.projectcapstone.databinding.ActivityRegisterBinding
import com.candra.projectcapstone.helper.Helper

class RegisterActivity: AppCompatActivity()
{
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.apply {
            textviewExistAcount.setOnClickListener {
                Helper.toNavigateLoginActivity(this@RegisterActivity)
                finish()
            }
        }
    }
}