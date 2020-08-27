package com.example.watchmore.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.example.watchmore.databinding.ActivityMyRecommendBinding
import com.example.watchmore.viewmodel.MyRecommendViewModel

class MyRecommendActivity : AppCompatActivity() {

    private lateinit var myRecommendBinding: ActivityMyRecommendBinding
    private lateinit var myRecommendViewModel: MyRecommendViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        myRecommendViewModel =
            ViewModelProviders.of(this).get(MyRecommendViewModel::class.java)
        myRecommendBinding = ActivityMyRecommendBinding.inflate(layoutInflater,null,false)
        myRecommendBinding.data = myRecommendViewModel
        myRecommendBinding.lifecycleOwner = this
        setContentView(myRecommendBinding.root)
    }
}
