package com.candra.projectcapstone.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.candra.projectcapstone.R
import com.candra.projectcapstone.databinding.FragmentMusicBinding
import com.candra.projectcapstone.helper.Helper.makeToast

class MusicFragment: Fragment() {

    private var _binding: FragmentMusicBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMusicBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setBackgroundCardView()
        setActionComponentClick()
    }


    private fun setBackgroundCardView(){
        binding.apply {
            containerBookmark.background = ContextCompat.getDrawable(requireActivity(), R.drawable.bookmark)
            containerNotification.background = ContextCompat.getDrawable(requireActivity(),R.drawable.notif)
        }
    }

    private fun setActionComponentClick(){
        binding.apply {
            containerBookmark.setOnClickListener { makeToast(requireActivity()) }
            containerNotification.setOnClickListener { makeToast(requireActivity()) }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}