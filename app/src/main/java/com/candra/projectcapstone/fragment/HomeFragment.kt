package com.candra.projectcapstone.fragment

import android.annotation.SuppressLint
import android.app.Activity.RESULT_OK
import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.provider.Settings
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import com.candra.projectcapstone.R
import com.candra.projectcapstone.activity.home.ListDetailScan
import com.candra.projectcapstone.databinding.FragmentHomeBinding
import com.candra.projectcapstone.helper.CameraUtils.createCustomTemptFile
import com.candra.projectcapstone.helper.Constant
import com.candra.projectcapstone.helper.Helper.makeToast
import com.candra.projectcapstone.ml.ModelFerLarasMetadata
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import org.tensorflow.lite.DataType
import org.tensorflow.lite.support.image.ImageProcessor
import org.tensorflow.lite.support.image.TensorImage
import org.tensorflow.lite.support.image.ops.ResizeOp
import org.tensorflow.lite.support.image.ops.TransformToGrayscaleOp
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer
import java.io.File

class HomeFragment: Fragment()
{

    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!
    private lateinit var currentPhotoPath: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setBackgroundToCardView()
        setActionComponentClick()
    }

    private fun setBackgroundToCardView(){
        binding.apply {
            cardviewContainerFace.background =
                ContextCompat.getDrawable(requireActivity(), R.drawable.background_image_scan)
            containerBookmark.background =
                ContextCompat.getDrawable(requireActivity(), R.drawable.bookmark)
            containerNotification.background =
                ContextCompat.getDrawable(requireActivity(), R.drawable.notif)
        }
    }

    private fun setActionComponentClick(){
        binding.apply {
            btnAllMusic.setOnClickListener {
                val bottomNavigation =
                    requireActivity().findViewById<BottomNavigationView>(R.id.bottom_navigation)
                bottomNavigation.selectedItemId = R.id.music
            }
            cardviewContainerFace.setOnClickListener {
                requestPermissionCamera()
            }
            containerBookmark.setOnClickListener {
                makeToast(requireActivity())
            }
            containerNotification.setOnClickListener {
                makeToast(requireActivity())
            }
        }
    }

    @SuppressLint("QueryPermissionsNeeded")
    private fun setCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        intent.resolveActivity(requireActivity().packageManager)

        createCustomTemptFile(requireActivity().application).also {
            val photoUri: Uri = FileProvider.getUriForFile(
                requireActivity(),
                "com.candra.projectcapstone",
                it
            )
            currentPhotoPath = it.absolutePath
            intent.putExtra(MediaStore.EXTRA_OUTPUT,photoUri)
            launcherIntentCamera.launch(intent)
        }
    }

    private val launcherIntentCamera = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ){
        if (it.resultCode == RESULT_OK){
            val myFile = File(currentPhotoPath)

            val result = BitmapFactory.decodeFile(myFile.path)

            setTensorFlow(result,myFile)

        }
    }

    private val tfImageProccessor by lazy {
        ImageProcessor.Builder()
            .add(ResizeOp(48,48, ResizeOp.ResizeMethod.NEAREST_NEIGHBOR))
            .add(TransformToGrayscaleOp())
            .build()
    }

    private var tfImage = TensorImage(DataType.FLOAT32)

    private fun setTensorFlow(bitmap: Bitmap, fileScanResult: File){
        val model = ModelFerLarasMetadata.newInstance(requireActivity())
        val byteBuffer = TensorBuffer.createFixedSize(intArrayOf(1,48,48,1), DataType.FLOAT32)
        val newBitmap = bitmap.copy(Bitmap.Config.ARGB_8888,true)

        tfImage.load(newBitmap)
        tfImage = tfImageProccessor.process(tfImage)
        byteBuffer.loadBuffer(tfImage.buffer)

        val outputs = model.process(byteBuffer)

        val highProbability = outputs.probabilityAsCategoryList.apply {
            sortByDescending { it.score }
        }
        val resultScanFace = highProbability[0].label

        sendDataToResultActivity(fileScanResult,resultScanFace)
    }

    private fun sendDataToResultActivity(bitmap: File, resultScanFace: String){
        Intent(requireActivity(), ListDetailScan::class.java).apply {
            putExtra(Constant.PICTURE,bitmap)
            putExtra(Constant.RESULT_SCAN_FACE,resultScanFace)
        }.also { startActivity(it) }
    }

    private fun requestPermissionCamera(){
        Dexter.withContext(requireActivity())
            .withPermission(
                android.Manifest.permission.CAMERA
            ).withListener(listenerDexter).onSameThread().check()
    }

    private val listenerDexter = object: PermissionListener{
        override fun onPermissionGranted(p0: PermissionGrantedResponse?) {
            setCamera()
        }

        override fun onPermissionDenied(p0: PermissionDeniedResponse?) {
            showDialogPermissionDenied()
        }

        override fun onPermissionRationaleShouldBeShown(
            p0: PermissionRequest?,
            p1: PermissionToken?
        ) {
            showDialogPermissionDenied()
        }
    }

    private fun showDialogPermissionDenied() {
        AlertDialog.Builder(requireActivity())
            .setMessage("Aplikasi ini membutuhkan fitur perizinan camera anda" +
                    "Jika fitur ini tidak diaktifkan. Anda tidak bisa menggunakan fitur ini.." +
                    "Silahkan pergi ke Setting Anda")
            .setTitle("Peringatan")
            .setIcon(R.drawable.logo_icon)
            .setPositiveButton("Pergi ke setting"){_,_ ->
                try {
                    Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                        data = Uri.fromParts("package",requireActivity().packageName,null)
                    }.also { requireActivity().startActivity(it) }
                }catch (e: ActivityNotFoundException){
                    e.printStackTrace()
                    Log.d(TAG, "showDialogPermissionDenied: ${e.message.toString()}")
                }
            }
            .setNegativeButton("Cancel"){dialog,_ ->
                dialog.dismiss()
            }.show()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object{
        const val TAG = "Home Fragment"
    }


}