package com.example.watchmore.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.example.watchmore.databinding.ActivityThemeBinding
import com.example.watchmore.util.ThemeUtil
import com.example.watchmore.viewmodel.ThemeViewModel


class ThemeActivity : AppCompatActivity() {

    private lateinit var themeViewModel: ThemeViewModel
    private lateinit var themeBinding : ActivityThemeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        ThemeUtil.setBaseTheme(this)
        super.onCreate(savedInstanceState)
        themeViewModel =
            ViewModelProviders.of(this).get(ThemeViewModel::class.java)
        themeBinding = ActivityThemeBinding.inflate(layoutInflater,null,false)
        themeBinding.data = themeViewModel
        themeBinding.lifecycleOwner = this
        themeBinding.toolbar.setNavigationOnClickListener(View.OnClickListener {
            finish()
        })
        setContentView(themeBinding.root)
    }

    override fun onBackPressed() {
        var intent = Intent()
        intent.putExtra("isThemeChanged",true)
        setResult(1,intent)
    }
}
