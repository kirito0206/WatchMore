package com.example.watchmore.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.example.watchmore.databinding.ActivityAnimeDetailBinding
import com.example.watchmore.util.ThemeUtil
import com.example.watchmore.viewmodel.AnimeDetailViewModel

class AnimeDetailActivity : AppCompatActivity() {

    private lateinit var detailBinding : ActivityAnimeDetailBinding
    companion object {
        var detailViewModel: AnimeDetailViewModel? = null
        fun getViewModel(): AnimeDetailViewModel = detailViewModel!!
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        ThemeUtil.setBaseTheme(this)
        super.onCreate(savedInstanceState)
        detailViewModel =
            ViewModelProviders.of(this).get(AnimeDetailViewModel::class.java)
        detailBinding = ActivityAnimeDetailBinding.inflate(layoutInflater,null,false)
        detailBinding.lifecycleOwner = this
        detailViewModel!!.animeId = intent.getIntExtra("animeId",1)
        detailViewModel!!.loadDatas()
        detailBinding.data = detailViewModel
        setContentView(detailBinding.root)
    }
}