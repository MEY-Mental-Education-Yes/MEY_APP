package com.candra.projectcapstone.fragment

import android.annotation.SuppressLint
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

@SuppressLint("SetTextI18n")
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
        setTextChip()
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

    private fun setTextChip(){
        binding.apply {
            chipAngry.text = Constant.ANGRY
            chipHappy.text = Constant.HAPPY
            chipNetral.text = Constant.NEUTRAL
            chipSad.text = Constant.SAD
            chipScarry.text = Constant.FEAR
            chipSuprise.text = Constant.SURPRISE
            chipDisgust.text = Constant.DISGUST
        }

    }

    private fun setActionComponentClick(){
        binding.apply {
            containerBookmark.setOnClickListener { makeToast(requireActivity()) }
            containerNotification.setOnClickListener { makeToast(requireActivity()) }
            setDefaultListMusic()
            chipAngry.setOnClickListener {
               setChipAndTextView(Constant.ANGRY)
            }
            chipHappy.setOnClickListener {
                setChipAndTextView(Constant.HAPPY)
            }
            chipNetral.setOnClickListener {
                setChipAndTextView(Constant.NEUTRAL)
            }
            chipSad.setOnClickListener {
                setChipAndTextView(Constant.SAD)
            }
            chipScarry.setOnClickListener {
                setChipAndTextView(Constant.FEAR)
            }
            chipSuprise.setOnClickListener {
                setChipAndTextView(Constant.SURPRISE)
            }
            chipDisgust.setOnClickListener {
                setChipAndTextView(Constant.DISGUST)
            }

        }
    }

    private fun setChipAndTextView(category: String){
        listViewModel.getAllMusicList(category)
        binding.textYourMood.text = "list of songs based on your $category mood "
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
            textYourMood.text = "list of songs based on your ${Constant.HAPPY} mood "
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