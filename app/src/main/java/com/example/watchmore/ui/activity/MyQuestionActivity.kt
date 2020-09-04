package com.example.watchmore.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.example.watchmore.databinding.ActivityMyQuestionBinding
import com.example.watchmore.util.ThemeUtil
import com.example.watchmore.viewmodel.MyQuestionViewModel

class MyQuestionActivity : AppCompatActivity() {

    private lateinit var myQuestionViewModel: MyQuestionViewModel
    private lateinit var myQuestionBinding : ActivityMyQuestionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        ThemeUtil.setBaseTheme(this)
        super.onCreate(savedInstanceState)
        myQuestionViewModel =
            ViewModelProviders.of(this).get(MyQuestionViewModel::class.java)
        var userId = intent.getIntExtra("userid",-1)
        if (userId != -1)
            myQuestionViewModel.userid = userId
        myQuestionBinding = ActivityMyQuestionBinding.inflate(layoutInflater,null,false)
        myQuestionBinding.data = myQuestionViewModel
        myQuestionBinding.lifecycleOwner = this
        setContentView(myQuestionBinding.root)
    }
}
