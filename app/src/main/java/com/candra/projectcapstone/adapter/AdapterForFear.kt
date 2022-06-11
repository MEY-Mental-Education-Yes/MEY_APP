package com.candra.projectcapstone.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.candra.projectcapstone.activity.home.ExoPlayerMedia
import com.candra.projectcapstone.databinding.ListItemMusicBinding
import com.candra.projectcapstone.helper.Constant
import com.candra.projectcapstone.helper.Helper
import com.candra.projectcapstone.model.ListMusicModel

class AdapterForFear(): RecyclerView.Adapter<AdapterForFear.FearViewHolder>() {

    class FearViewHolder(private val binding: ListItemMusicBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(listMusic: ListMusicModel){
            with(binding){
                titleMusic.text = listMusic.title
                descriptionMusic.text = listMusic.url
                artistMusic.text = listMusic.artis
                Helper.imageScreen(imageLogoMusic,listMusic.image)
                containerMusic.setOnClickListener {
                    Intent(itemView.context,ExoPlayerMedia::class.java).apply {
                        putExtra(Constant.KEY_STRING,listMusic.url)
                        putExtra(Constant.KEY_TITLE,listMusic.title)
                    }.also { itemView.context.startActivity(it) }
                }
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AdapterForFear.FearViewHolder {
        return FearViewHolder(ListItemMusicBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: AdapterForFear.FearViewHolder, position: Int) {
        holder.bind(listMusic = differ.currentList[position])
    }

    override fun getItemCount(): Int = differ.currentList.size

    private val differ = AsyncListDiffer(this,object: DiffUtil.ItemCallback<ListMusicModel>(){
        override fun areItemsTheSame(oldItem: ListMusicModel, newItem: ListMusicModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ListMusicModel, newItem: ListMusicModel): Boolean {
            return oldItem == newItem
        }

    })

    fun addAllDataMusic(listData: List<ListMusicModel>){
        differ.submitList(listData)
    }
}