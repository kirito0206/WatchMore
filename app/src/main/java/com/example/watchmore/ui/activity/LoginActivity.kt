package com.example.watchmore.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.example.watchmore.databinding.ActivityLoginBinding
import com.example.watchmore.util.ThemeUtil
import com.example.watchmore.viewmodel.LoginViewModel

class LoginActivity : AppCompatActivity() {

    private lateinit var loginBinding : ActivityLoginBinding
    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        ThemeUtil.setBaseTheme(this)
        super.onCreate(savedInstanceState)
        loginViewModel =
            ViewModelProviders.of(this).get(LoginViewModel::class.java)
        loginBinding = ActivityLoginBinding.inflate(layoutInflater,null,false)
        loginBinding.data = loginViewModel
        loginBinding.lifecycleOwner = this
        setContentView(loginBinding.root)
    }
}
