package com.example.watchmore.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.example.watchmore.databinding.ActivityEditBinding
import com.example.watchmore.util.ThemeUtil
import com.example.watchmore.viewmodel.EditViewModel
import me.iwf.photopicker.PhotoPickerActivity

class EditActivity : AppCompatActivity() {

    private lateinit var editBinding : ActivityEditBinding
    private lateinit var editViewModel: EditViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        ThemeUtil.setBaseTheme(this)
        super.onCreate(savedInstanceState)
        editViewModel =
            ViewModelProviders.of(this).get(EditViewModel::class.java)
        editBinding = ActivityEditBinding.inflate(layoutInflater,null,false)
        editBinding.data = editViewModel
        editBinding.lifecycleOwner = this
        editViewModel.editType = intent.getIntExtra("editType",1)
        setContentView(editBinding.root)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == 0) {
            if (data != null) {
                editViewModel.photos.value = data.getStringArrayListExtra(PhotoPickerActivity.KEY_SELECTED_PHOTOS)
            }
        }
    }
}
