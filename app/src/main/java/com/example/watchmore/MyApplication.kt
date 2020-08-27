package com.example.watchmore

import android.app.Application
import com.nostra13.universalimageloader.core.ImageLoader
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initImageLoader()
    }

    private fun initImageLoader() {
        val configuration = ImageLoaderConfiguration.createDefault(this)
        ImageLoader.getInstance().init(configuration)
    }
}