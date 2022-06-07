package com.candra.projectcapstone.activity.onboarding

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.candra.projectcapstone.activity.onboarding.screen.FragmentOne
import com.candra.projectcapstone.activity.onboarding.screen.FragmentTwo
import com.candra.projectcapstone.databinding.FragmentViewPagerBinding

class ViewPagerActivity: AppCompatActivity()
{
    private lateinit var binding: FragmentViewPagerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentViewPagerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setFragment()
    }

    private fun setFragment(){
        val fragmentList = arrayListOf(
            FragmentOne(),
            FragmentTwo(),
        )

        val adapterListFragment = ViewPagerAdapter(
            fragmentList,
            supportFragmentManager,
            lifecycle
        )

        binding.viewPager2.adapter = adapterListFragment
    }


}