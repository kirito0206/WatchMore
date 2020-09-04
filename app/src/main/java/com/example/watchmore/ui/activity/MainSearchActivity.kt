package com.example.watchmore.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.example.watchmore.databinding.ActivityMainSearchBinding
import com.example.watchmore.util.ThemeUtil
import com.example.watchmore.viewmodel.MainSearchViewModel

class MainSearchActivity : AppCompatActivity() {

    private lateinit var mainSearchBinding : ActivityMainSearchBinding
    private lateinit var mainSearchViewModel: MainSearchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        ThemeUtil.setBaseTheme(this)
        super.onCreate(savedInstanceState)
        mainSearchViewModel =
            ViewModelProviders.of(this).get(MainSearchViewModel::class.java)
        var tagid = intent.getStringExtra("tagid")
        mainSearchViewModel.tagid = tagid
        mainSearchViewModel.loadDatas()
        mainSearchBinding = ActivityMainSearchBinding.inflate(layoutInflater,null,false)
        mainSearchBinding.data = mainSearchViewModel
        mainSearchBinding.lifecycleOwner = this
        setContentView(mainSearchBinding.root)
    }
}
