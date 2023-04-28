package com.papayacoders.youtubevideoextractorwithnewpipe

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.papayacoders.youtubevideoextractorwithnewpipe.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.schabi.newpipe.extractor.services.youtube.YoutubeService
import org.schabi.newpipe.extractor.services.youtube.extractors.YoutubeStreamExtractor

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            if (binding.link.text!!.isEmpty())
                Toast.makeText(this, "Please insert link.", Toast.LENGTH_SHORT).show()
            else {
                fetchVideoUrl()
            }
        }
    }

    private fun fetchVideoUrl() {
        var streamUrl: String = ""
        var audioUrl: String = ""

        lifecycleScope.launch(Dispatchers.IO) {
            try {
                val extractor =
                    YoutubeService(0).getStreamExtractor(binding.link.text.toString()) as YoutubeStreamExtractor
                extractor.fetchPage()
                Log.d("shubh", "fetchVideoUrl: ${extractor.videoOnlyStreams}")

//                extractor.videoStreams.component1()
//                extractor.videoStreams.component2()
//                extractor.videoStreams.component3()
//                extractor.videoStreams.component4()
//                extractor.videoStreams.component5()
                if (extractor.videoStreams.isNotEmpty()) {
                    streamUrl = extractor.videoStreams.first().url ?: ""
                    audioUrl = extractor.audioStreams.first().url ?: ""
                    extractor
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }

            Log.d("shubh", "fetchVideoUrl: $streamUrl")
        }
    }
}