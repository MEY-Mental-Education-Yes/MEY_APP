package com.candra.projectcapstone.activity.onboarding.screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import com.candra.projectcapstone.R
import com.candra.projectcapstone.databinding.FragmentScreenTwoBinding
import com.candra.projectcapstone.helper.Helper
import com.candra.projectcapstone.helper.Helper.IMAGE_SCREEN_TWO

class FragmentTwo : Fragment()
{
    private var _binding: FragmentScreenTwoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentScreenTwoBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewPager2 = activity?.findViewById<ViewPager2>(R.id.view_pager_2)

        binding.apply {
            btnNextScreenOne.setOnClickListener {
                viewPager2?.currentItem = 2
            }
            btnSkipScreenOne.setOnClickListener {
                Helper.toScreenHome(requireActivity())
                Helper.setCheckedData(lifecycleScope,requireActivity())
            }
            Helper.imageScreen(ivScreenOne,IMAGE_SCREEN_TWO)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}