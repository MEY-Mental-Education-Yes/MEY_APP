package com.candra.projectcapstone.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.candra.projectcapstone.R
import com.candra.projectcapstone.adapter.AdapterForFear
import com.candra.projectcapstone.databinding.FragmentMusicBinding
import com.candra.projectcapstone.helper.Constant
import com.candra.projectcapstone.helper.Helper.makeToast
import com.candra.projectcapstone.viewmodel.ListMusicViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MusicFragment: Fragment() {

    private var _binding: FragmentMusicBinding? = null
    private val binding get() = _binding!!
    private  lateinit var listViewModel: ListMusicViewModel
    private val adapterMain by lazy { AdapterForFear() }

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
        listViewModel = ViewModelProvider(this)[ListMusicViewModel::class.java]
        setActionComponentClick()
        setBackgroundCardView()
        setObserverData()
        setAdapterToMusicFragment()
    }

    private fun setAdapterToMusicFragment(){
        binding.listMusic.apply {
            layoutManager = LinearLayoutManager(requireActivity())
            adapter = adapterMain
        }
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
            setDefaultListMusic()
            chipAngry.setOnClickListener {
                listViewModel.getAllMusicList(Constant.ANGRY)
                chipAngry.text = Constant.ANGRY
            }
            chipHappy.setOnClickListener {
                listViewModel.getAllMusicList(Constant.HAPPY)
                chipHappy.text = Constant.HAPPY
            }
            chipNetral.setOnClickListener {
                listViewModel.getAllMusicList(Constant.NEUTRAL)
                chipNetral.text = Constant.NEUTRAL
            }
            chipSad.setOnClickListener {
                listViewModel.getAllMusicList(Constant.SAD)
                chipSad.text = Constant.SAD
            }
            chipScarry.setOnClickListener {
                listViewModel.getAllMusicList(Constant.FEAR)
                chipScarry.text = Constant.FEAR
            }
            chipSuprise.setOnClickListener {
                listViewModel.getAllMusicList(Constant.DISGUST)
                chipScarry.text = Constant.DISGUST
            }
        }
    }

    private fun setObserverData(){
        listViewModel.listMusicData.observe(viewLifecycleOwner){
            adapterMain.addAllDataMusic(it)
        }

        listViewModel.loading.observe(viewLifecycleOwner){
            showShimmerEffect(it)
        }
    }


    private fun setDefaultListMusic(){
        listViewModel.getAllMusicList(Constant.HAPPY)
        binding.apply {
            chipHappy.isChecked = true
            chipHappy.text = Constant.HAPPY
        }
    }

    private fun showShimmerEffect(show: Boolean){
        binding.apply {
            if (show){
                shimmerEffect.startShimmer()
                shimmerEffect.visibility = View.VISIBLE
                listMusic.visibility = View.GONE
            }else{
                shimmerEffect.hideShimmer()
                shimmerEffect.visibility = View.GONE
                listMusic.visibility = View.VISIBLE
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}