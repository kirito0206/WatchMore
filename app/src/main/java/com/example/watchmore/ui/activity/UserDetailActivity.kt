package com.example.watchmore.ui.activity

import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.example.watchmore.R
import com.example.watchmore.databinding.ActivityUserDetailBinding
import com.example.watchmore.util.RealPathFromUriUtils
import com.example.watchmore.util.ThemeUtil
import com.example.watchmore.util.debug
import com.example.watchmore.viewmodel.UserDetailViewModel

class UserDetailActivity : AppCompatActivity() {

    private lateinit var userDetailViewModel: UserDetailViewModel
    private lateinit var userDetailBinding : ActivityUserDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        ThemeUtil.setBaseTheme(this)
        super.onCreate(savedInstanceState)
        userDetailViewModel =
            ViewModelProviders.of(this).get(UserDetailViewModel::class.java)
        userDetailBinding = DataBindingUtil.setContentView(this, R.layout.activity_user_detail)
        userDetailViewModel.userAvatar.value = intent.getStringExtra("userAvatar")
        userDetailViewModel.userName.value = intent.getStringExtra("userName")
        userDetailViewModel.userEmail.value = intent.getStringExtra("userEmail")
        userDetailBinding.data = userDetailViewModel
        userDetailBinding.lifecycleOwner = this
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            0 -> {
                data?.data?.let { RealPathFromUriUtils.getRealPathFromUri(this,it)?.let { it1 ->
                    userDetailViewModel.userAvatar.value = it1
                    debug(it1)
                } }
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
                userDetailViewModel.choosePhoto(this)
            }
        }
    }
}
