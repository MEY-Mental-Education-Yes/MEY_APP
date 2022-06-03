package com.candra.projectcapstone.activity.onboarding.screen

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.candra.projectcapstone.databinding.FragmentScreenThreeBinding
import com.candra.projectcapstone.helper.Helper
import com.candra.projectcapstone.helper.Helper.IMAGE_SCREEN_THREE
import com.candra.projectcapstone.helper.LocalShared
import kotlinx.coroutines.launch

class FragmentThree: Fragment()
{
    private var _binding: FragmentScreenThreeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        _binding = FragmentScreenThreeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            btnNextScreenOne.setOnClickListener {
                Helper.toScreenHome(requireActivity())
                lifecycleScope.launch {
                    Helper.setCheckedData(lifecycleScope,requireActivity())
                }
            }
            btnSkipScreenOne.setOnClickListener {
                Helper.toScreenHome(requireActivity())
                Helper.setCheckedData(lifecycleScope,requireActivity())
            }
            Helper.imageScreen(ivScreenOne,IMAGE_SCREEN_THREE)
        }
    }



    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}