package com.example.watchmore.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.example.watchmore.databinding.ActivityMyRecommendBinding
import com.example.watchmore.util.ThemeUtil
import com.example.watchmore.viewmodel.MyRecommendViewModel

class MyRecommendActivity : AppCompatActivity() {

    private lateinit var myRecommendBinding: ActivityMyRecommendBinding
    private lateinit var myRecommendViewModel: MyRecommendViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        ThemeUtil.setBaseTheme(this)
        super.onCreate(savedInstanceState)
        myRecommendViewModel =
            ViewModelProviders.of(this).get(MyRecommendViewModel::class.java)
        var userId = intent.getIntExtra("userid",-1)
        if (userId != -1)
            myRecommendViewModel.userid = userId
        myRecommendBinding = ActivityMyRecommendBinding.inflate(layoutInflater,null,false)
        myRecommendBinding.data = myRecommendViewModel
        myRecommendBinding.lifecycleOwner = this
        setContentView(myRecommendBinding.root)
    }
}
