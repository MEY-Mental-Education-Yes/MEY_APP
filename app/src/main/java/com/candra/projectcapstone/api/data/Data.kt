package com.candra.projectcapstone.api.data


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("Artist")
    val artist: String,
    @SerializedName("Category")
    val category: String,
    @SerializedName("Id")
    val id: Int,
    @SerializedName("Image")
    val image: String,
    @SerializedName("Link")
    val link: String,
    @SerializedName("Title")
    val title: String
)