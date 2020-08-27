package com.example.watchmore.viewmodel

import android.app.Activity
import android.content.Intent
import android.view.View
import androidx.lifecycle.ViewModel
import com.example.watchmore.ui.activity.ThemeActivity
import com.example.watchmore.util.ThemeUtil


class ThemeViewModel : ViewModel() {

    var isChanged = false

    fun onclickTheme(view : View, themeStyle : Int){
        if (ThemeUtil.setNewTheme(view.context,themeStyle)){
            view.context.startActivity(Intent(view.context, ThemeActivity::class.java))
            (view.context as Activity).overridePendingTransition(0, 0)
            (view.context as Activity).finish()
            isChanged = true
        }
    }
}