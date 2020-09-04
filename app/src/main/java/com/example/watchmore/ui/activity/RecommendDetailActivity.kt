package com.example.watchmore.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.example.watchmore.databinding.ActivityRecommendDetailBinding
import com.example.watchmore.util.ThemeUtil
import com.example.watchmore.viewmodel.RecommendDetailViewModel


class RecommendDetailActivity : AppCompatActivity() {
    private lateinit var recommendDetailViewModel: RecommendDetailViewModel
    private lateinit var recommendDetailBinding : ActivityRecommendDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        ThemeUtil.setBaseTheme(this)
        super.onCreate(savedInstanceState)
        recommendDetailViewModel =
            ViewModelProviders.of(this).get(RecommendDetailViewModel::class.java)
        var dramaid = intent.getIntExtra("dramaid",-1)
        if (dramaid != -1)
            recommendDetailViewModel.dramaid = dramaid
        recommendDetailViewModel.loadDatas()
        recommendDetailBinding = ActivityRecommendDetailBinding.inflate(layoutInflater,null,false)
        recommendDetailBinding.data = recommendDetailViewModel
        recommendDetailBinding.lifecycleOwner = this
        setContentView(recommendDetailBinding.root)
    }
}