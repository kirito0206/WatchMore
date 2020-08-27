package com.example.watchmore.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.example.watchmore.R
import com.example.watchmore.databinding.ActivityUserDetailBinding
import com.example.watchmore.util.ThemeUtil
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
}
