package com.example.watchmore.util

import android.content.Context
import android.util.Log
import android.widget.Toast

fun Any.debug(msg: String) {
    Log.d(javaClass.simpleName, msg)
}

fun Any.toast(context: Context,msg: String) {
    Toast.makeText(context,msg,Toast.LENGTH_SHORT).show()
}

