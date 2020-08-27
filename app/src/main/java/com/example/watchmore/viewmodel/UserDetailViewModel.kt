package com.example.watchmore.viewmodel

import android.app.Activity
import android.view.View
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UserDetailViewModel : ViewModel() {

    var userAvatar = MutableLiveData<String>()
    var userName = MutableLiveData<String>()
    var userEmail = MutableLiveData<String>()

    fun update(view : View){

    }

    fun close(view: View) = (view.context as Activity).finish()
}