package com.papayacoders.youtubevideoextractorwithnewpipe

import android.app.Application
import okhttp3.OkHttpClient
import org.schabi.newpipe.extractor.NewPipe
import java.util.concurrent.TimeUnit

class MyApplication : Application(){


    var pipeDownloader: PipeDownloader = PipeDownloader(
        OkHttpClient.Builder()
        .readTimeout(30, TimeUnit.SECONDS)
        .build())

    override fun onCreate() {
        super.onCreate()

        NewPipe.init(pipeDownloader)
    }

}