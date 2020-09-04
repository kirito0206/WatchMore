package com.example.watchmore.ui.activity

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.example.watchmore.databinding.ActivityEditQuestionBinding
import com.example.watchmore.util.RealPathFromUriUtils
import com.example.watchmore.util.SPUtils
import com.example.watchmore.util.ThemeUtil
import com.example.watchmore.util.debug
import com.example.watchmore.viewmodel.QuestionEditViewModel


class QuestionEditActivity : AppCompatActivity() {

    private lateinit var editBinding : ActivityEditQuestionBinding
    private lateinit var questionEditViewModel: QuestionEditViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        ThemeUtil.setBaseTheme(this)
        super.onCreate(savedInstanceState)
        questionEditViewModel =
            ViewModelProviders.of(this).get(QuestionEditViewModel::class.java)
        editBinding = ActivityEditQuestionBinding.inflate(layoutInflater,null,false)
        editBinding.data = questionEditViewModel
        editBinding.lifecycleOwner = this
        setContentView(editBinding.root)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            0 -> {
                var photos = questionEditViewModel.photos.value
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
                questionEditViewModel.photos.value = photos
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
                questionEditViewModel.choosePhoto(this)
            }
        }
    }
}
