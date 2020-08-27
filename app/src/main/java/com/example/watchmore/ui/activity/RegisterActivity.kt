package com.example.watchmore.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.example.watchmore.databinding.ActivityRegisterBinding
import com.example.watchmore.util.ThemeUtil
import com.example.watchmore.viewmodel.RegisterViewModel

class RegisterActivity : AppCompatActivity() {

    private lateinit var registerBinding : ActivityRegisterBinding
    private lateinit var registerViewModel: RegisterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        ThemeUtil.setBaseTheme(this)
        super.onCreate(savedInstanceState)
        registerViewModel =
            ViewModelProviders.of(this).get(RegisterViewModel::class.java)
        registerBinding = ActivityRegisterBinding.inflate(layoutInflater,null,false)
        registerBinding.data = registerViewModel
        registerBinding.lifecycleOwner = this
        setContentView(registerBinding.root)
    }
}
