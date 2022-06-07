package com.candra.projectcapstone.activity.onboarding.screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import com.candra.projectcapstone.R
import com.candra.projectcapstone.databinding.FragmentScreenOneBinding
import com.candra.projectcapstone.helper.Helper.IMAGE_SCREEN_ONE
import com.candra.projectcapstone.helper.Helper.imageScreen
import com.candra.projectcapstone.helper.Helper.setCheckedData
import com.candra.projectcapstone.helper.Helper.toScreenHome

class FragmentOne: Fragment()
{
    private var _binding: FragmentScreenOneBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentScreenOneBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewPager = activity?.findViewById<ViewPager2>(R.id.view_pager_2)

        binding.apply {
            btnNextScreenOne.setOnClickListener {
                viewPager?.currentItem = 1
            }

            btnSkipScreenOne.setOnClickListener {
                toScreenHome(requireActivity())
                setCheckedData(lifecycleScope,requireActivity())
            }

            imageScreen(ivScreenOne,IMAGE_SCREEN_ONE)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}