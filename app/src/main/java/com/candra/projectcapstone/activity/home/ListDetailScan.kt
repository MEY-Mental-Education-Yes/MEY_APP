package com.candra.projectcapstone.activity.home

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import coil.transform.RoundedCornersTransformation
import com.candra.projectcapstone.R
import com.candra.projectcapstone.adapter.AdapterForFear
import com.candra.projectcapstone.databinding.ResultActivityBinding
import com.candra.projectcapstone.helper.Constant
import com.candra.projectcapstone.helper.Constant.PICTURE
import com.candra.projectcapstone.helper.Constant.RESULT_SCAN_FACE
import com.candra.projectcapstone.viewmodel.ListMusicViewModel
import com.google.android.material.textview.MaterialTextView
import dagger.hilt.android.AndroidEntryPoint
import java.io.File

@AndroidEntryPoint
class ListDetailScan: AppCompatActivity()
{

    private lateinit var binding: ResultActivityBinding
    private var getFile: File? = null
    private var getIsBackCamera:Boolean = false
    private val listMusicViewModel by viewModels<ListMusicViewModel>()
    private val adapterMusic by lazy { AdapterForFear() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ResultActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setBackgroundCardView()
        setActionComponentClick()
        readObserverData()
        setAdapterToListDetailScan()
    }

    private fun setBackgroundCardView(){
        binding.apply {
            containerImageview.background = ContextCompat.getDrawable(this@ListDetailScan, R.drawable.turn_back)
        }
    }

    private fun setActionComponentClick(){
        binding.apply {
            containerImageview.setOnClickListener {
                toBackHomeFragment()
            }
            getResultFromCamera(imageScanUser,resultTextScanUser,textDescriptionName)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun getResultFromCamera(imageView: ImageView, textResultScan: MaterialTextView, textDescriptionResultScan: MaterialTextView){
        val resultImage = intent.getSerializableExtra(PICTURE) as File
        val resultScanFace = intent.getStringExtra(RESULT_SCAN_FACE)
        val convertFileToBitmap = BitmapFactory.decodeFile(resultImage.path)


        imageView.load(convertFileToBitmap){
            transformations(RoundedCornersTransformation(20f))
        }
        textResultScan.text = resultScanFace
        textDescriptionResultScan.text = "Lagu untuk hasil ${resultScanFace}, Selamat menikmati lagu ${resultScanFace}"

        resultScanFace?.let { setListMusicBasedResultFace(it) }
    }

    private fun setListMusicBasedResultFace(resultFace: String){
        when(resultFace){
            Constant.ANGRY -> {
                listMusicViewModel.getAllMusicList(Constant.ANGRY)
            }
            Constant.SAD -> {
                listMusicViewModel.getAllMusicList(Constant.SAD)
            }
            Constant.DISGUST -> {
                listMusicViewModel.getAllMusicList(Constant.DISGUST)
            }
            Constant.FEAR -> {
                listMusicViewModel.getAllMusicList(Constant.FEAR)
            }
            Constant.HAPPY -> {
                listMusicViewModel.getAllMusicList(Constant.HAPPY)
            }
            Constant.NEUTRAL -> {
                listMusicViewModel.getAllMusicList(Constant.NEUTRAL)
            }
        }
    }

    private fun setAdapterToListDetailScan(){
        binding.listMusicItem.apply {
            layoutManager = LinearLayoutManager(this@ListDetailScan)
            adapter = adapterMusic
        }
    }

    private fun readObserverData(){
        listMusicViewModel.listMusicData.observe(this){
            if (it != null){
                adapterMusic.addAllDataMusic(it)
            }
        }
        listMusicViewModel.loading.observe(this){
            showShimmerEffect(it)
        }
    }

    private fun showShimmerEffect(show: Boolean){
        binding.apply {
            if (show){
                shimmerEffect.startShimmer()
                shimmerEffect.visibility = View.VISIBLE
                listMusicItem.visibility = View.GONE
            }else{
                shimmerEffect.hideShimmer()
                shimmerEffect.visibility = View.GONE
                listMusicItem.visibility = View.VISIBLE
            }
        }
    }

    private fun toBackHomeFragment(){
        startActivity(Intent(this@ListDetailScan,MainActivity::class.java))
        finish()
    }
}