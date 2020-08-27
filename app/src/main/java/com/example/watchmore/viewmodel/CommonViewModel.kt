package com.example.watchmore.viewmodel

import android.content.Intent
import android.view.View
import androidx.lifecycle.ViewModel
import com.example.watchmore.ui.activity.AnimeDetailActivity

class CommonViewModel : ViewModel() {

    fun intentToAnimeDetail(view : View,animeId : Int){
        var intent = Intent(view.context, AnimeDetailActivity::class.java)
        intent.putExtra("animeId",animeId)
        view.context.startActivity(intent)
    }
}