package com.example.watchmore.ui.activity

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.example.watchmore.databinding.ActivityRecommendEditBinding
import com.example.watchmore.util.RealPathFromUriUtils
import com.example.watchmore.util.ThemeUtil
import com.example.watchmore.util.debug
import com.example.watchmore.viewmodel.RecommendEditViewModel


class RecommendEditActivity : AppCompatActivity() {
    private lateinit var editBinding : ActivityRecommendEditBinding
    private lateinit var recommendEditViewModel: RecommendEditViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        ThemeUtil.setBaseTheme(this)
        super.onCreate(savedInstanceState)
        recommendEditViewModel =
            ViewModelProviders.of(this).get(RecommendEditViewModel::class.java)
        editBinding = ActivityRecommendEditBinding.inflate(layoutInflater,null,false)
        editBinding.data = recommendEditViewModel
        editBinding.lifecycleOwner = this
        setContentView(editBinding.root)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            0 -> {
                var photos : ArrayList<String>? = null
                if (recommendEditViewModel.flag == 0)
                    photos = recommendEditViewModel.photos.value
                else
                    photos = recommendEditViewModel.animePics.value

                if (photos == null){
                    photos = ArrayList()
                }
                data?.data?.let { RealPathFromUriUtils.getRealPathFromUri(this,it)?.let { it1 ->
                    photos.add(
                        it1
                    )
                    debug(it1)
                } }
                debug("photossize"+photos.size.toString())
                if (recommendEditViewModel.flag == 0)
                    recommendEditViewModel.photos.value = photos
                else
                    recommendEditViewModel.animePics.value = photos
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            RESULT_OK -> if (grantResults.size == 2 && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED
            ) {
                recommendEditViewModel.choosePhoto(this)
            }
        }
    }
}