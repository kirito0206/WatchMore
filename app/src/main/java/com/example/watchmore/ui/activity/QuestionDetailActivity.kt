package com.example.watchmore.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.example.watchmore.databinding.ActivityQuestionDetailBinding
import com.example.watchmore.util.ThemeUtil
import com.example.watchmore.util.debug
import com.example.watchmore.viewmodel.QuestionDetaiViewModel

class QuestionDetailActivity : AppCompatActivity() {

    private lateinit var questionDetailViewModel: QuestionDetaiViewModel
    private lateinit var questionDetailBinding : ActivityQuestionDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        ThemeUtil.setBaseTheme(this)
        super.onCreate(savedInstanceState)
        questionDetailViewModel =
            ViewModelProviders.of(this).get(QuestionDetaiViewModel::class.java)
        var dramaid = intent.getIntExtra("dramaid",-1)
        if (dramaid != -1)
            questionDetailViewModel.dramaid = dramaid
        questionDetailViewModel.loadDatas()
        questionDetailBinding = ActivityQuestionDetailBinding.inflate(layoutInflater,null,false)
        questionDetailBinding.data = questionDetailViewModel
        questionDetailBinding.lifecycleOwner = this
        setContentView(questionDetailBinding.root)
    }
}