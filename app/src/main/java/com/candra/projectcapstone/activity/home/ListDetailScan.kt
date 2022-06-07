package com.candra.projectcapstone.activity.home

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import coil.load
import coil.transform.RoundedCornersTransformation
import com.candra.projectcapstone.R
import com.candra.projectcapstone.databinding.ResultActivityBinding
import com.candra.projectcapstone.helper.Constant.PICTURE
import com.candra.projectcapstone.helper.Constant.RESULT_SCAN_FACE
import com.google.android.material.textview.MaterialTextView
import java.io.File

class ListDetailScan: AppCompatActivity()
{

    private lateinit var binding: ResultActivityBinding
    private var getFile: File? = null
    private var getIsBackCamera:Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ResultActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setBackgroundCardView()
        setActionComponentClick()
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
            "Angry" -> {

            }
            "Sad" -> {

            }
        }
    }

    private fun toBackHomeFragment(){
        startActivity(Intent(this@ListDetailScan,MainActivity::class.java))
        finish()
    }
}