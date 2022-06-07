package com.candra.projectcapstone.activity.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.candra.projectcapstone.R
import com.candra.projectcapstone.databinding.ActivityMainBinding
import com.candra.projectcapstone.fragment.HomeFragment
import com.candra.projectcapstone.fragment.MusicFragment
import com.candra.projectcapstone.helper.Helper


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Helper.loadFragment(HomeFragment(),supportFragmentManager)
        setBottomNavigation()
    }

    private fun setBottomNavigation(){
        binding.bottomNavigation.setOnItemSelectedListener {
            when(it.itemId){
                R.id.home -> Helper.loadFragment(HomeFragment(),supportFragmentManager)
                R.id.music -> Helper.loadFragment(MusicFragment(),supportFragmentManager)
            }
            return@setOnItemSelectedListener true
        }
    }
}