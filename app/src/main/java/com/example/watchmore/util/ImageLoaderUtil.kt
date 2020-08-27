package com.example.watchmore.util

import android.content.Context
import android.graphics.Bitmap
import android.widget.ImageView
import com.example.watchmore.R
import com.nostra13.universalimageloader.core.DisplayImageOptions
import com.nostra13.universalimageloader.core.ImageLoader
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener


object ImageLoaderUtil {
    fun getImageLoader(context: Context?): ImageLoader {
        return ImageLoader.getInstance()
    }

    fun getPhotoImageOption(): DisplayImageOptions{
            val extra = 1
            return DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisk(true)
                .showImageForEmptyUri(R.drawable.banner_default)
                .showImageOnFail(R.drawable.banner_default)
                .showImageOnLoading(R.drawable.banner_default)
                .extraForDownloader(extra)
                .bitmapConfig(Bitmap.Config.RGB_565).build()
    }

    fun displayImage(
        context: Context?,
        imageView: ImageView?,
        url: String?,
        options: DisplayImageOptions?
    ) {
        getImageLoader(context).displayImage(url, imageView, options)
    }

    fun displayImage(
        context: Context?,
        imageView: ImageView?,
        url: String?,
        options: DisplayImageOptions?,
        listener: ImageLoadingListener?
    ) {
        getImageLoader(context).displayImage(url, imageView, options, listener)
    }
}