package com.candra.projectcapstone.activity.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.candra.projectcapstone.R
import com.candra.projectcapstone.databinding.ExoPlayerBinding
import com.candra.projectcapstone.helper.Constant
import com.candra.projectcapstone.helper.Helper.youtubeId
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener

class ExoPlayerMedia: AppCompatActivity()
{
    private lateinit var binding: ExoPlayerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ExoPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.containerImageview.setOnClickListener {
            onBackPressed()
        }
        setVideoFromYoutube()
    }

    private fun setVideoFromYoutube(){
        val getStringUrlForVideo = intent.getStringExtra(Constant.KEY_STRING)
        val titleStringForVideo = intent.getStringExtra(Constant.KEY_TITLE)
        binding.apply {
            lifecycle.addObserver(youtubePlayerView)
            youtubePlayerView.apply {
                addYouTubePlayerListener(youtubePlayerListener(getStringUrlForVideo))
            }
            home.text = titleStringForVideo
            binding.containerImageview.background = ContextCompat.getDrawable(this@ExoPlayerMedia,R.drawable.turn_back)
        }
    }

   private fun youtubePlayerListener(id: String?) = object: AbstractYouTubePlayerListener(){
        override fun onReady(youTubePlayer: YouTubePlayer) {
            id.youtubeId?.let { youTubePlayer.loadVideo(it,0f) }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.youtubePlayerView.release()
    }

}