package com.candra.projectcapstone.api.data


import com.google.gson.annotations.SerializedName

data class ResponseAllMusicList(
    @SerializedName("data")
    val data: List<Data>,
    @SerializedName("success")
    val success: Boolean
)