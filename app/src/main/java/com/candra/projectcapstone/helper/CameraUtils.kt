package com.candra.projectcapstone.helper

import android.annotation.SuppressLint
import android.content.Context
import android.os.Environment
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

object CameraUtils {

    private const val FILENAME_FORMAT = "dd-MM-yyyy"

    @SuppressLint("ConstantLocale")
    private val timeStampt: String = SimpleDateFormat(
        FILENAME_FORMAT,
        Locale.getDefault()
    ).format(System.currentTimeMillis())

    fun createCustomTemptFile(context: Context): File {
        val storage: File? = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(timeStampt,".jpg",storage)
    }
}