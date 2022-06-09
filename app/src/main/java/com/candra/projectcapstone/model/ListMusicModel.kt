package com.candra.projectcapstone.model

import android.os.Parcelable
import com.candra.projectcapstone.api.data.Data
import kotlinx.parcelize.Parcelize

@Parcelize
data class ListMusicModel(
    val id: Int,
    val title: String,
    val artis: String,
    val url: String,
    val image: String
): Parcelable

fun List<Data>.toGenerateListMusic(): MutableList<ListMusicModel> {
    val listMusic = mutableListOf<ListMusicModel>()
    this.forEach { listMusic.add(it.toListMusic()) }
    return listMusic
}

fun Data.toListMusic(): ListMusicModel{
    return ListMusicModel(
        id = this.id?: 0,
        title = this.title?: "",
        artis = this.artist ?: "",
        image = this.image ?: "",
        url = this.link ?: "",
    )
}
