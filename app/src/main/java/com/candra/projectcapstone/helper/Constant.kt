package com.candra.projectcapstone.helper

import androidx.datastore.preferences.core.booleanPreferencesKey

object Constant {

   val isCheckedOnBoarding = booleanPreferencesKey("isCheckedOnBoarding")
   const val PICTURE = "picture"
   const val RESULT_SCAN_FACE = "result_scan_face"
   const val BASE_URL = "https://modular-edge-352015.et.r.appspot.com/"
   const val ENDPOINT = "tampil/{Category}"
   const val CATEGORY = "Category"
   const val TAG = "RemtoeDataSource"
   const val SAD = "Sad"
   const val HAPPY = "Happiness"
   const val DISGUST = "Disgust"
   const val ANGRY = "Angry"
   const val NEUTRAL = "Neutral"
   const val FEAR = "Fear"
}