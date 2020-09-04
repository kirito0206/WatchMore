package com.example.watchmore.viewmodel

import android.app.Activity
import android.app.Application
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.watchmore.model.SearchModel
import com.example.watchmore.model.bean.animebean.AnimeData
import com.example.watchmore.model.bean.animebean.AnimeSearchResponse
import com.example.watchmore.model.network.repository.AnimeRepository
import com.example.watchmore.util.debug
import com.example.watchmore.util.toast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainSearchViewModel(application: Application) : AndroidViewModel(application) {

    private val response = MutableLiveData<AnimeSearchResponse>()
    private val repository by lazy { AnimeRepository() }
    private val searchModel by lazy { SearchModel() }
    val animesList = MutableLiveData<ArrayList<AnimeData>>()
    var tagid = ""

    fun loadDatas() {
        GlobalScope.launch(Dispatchers.Main) {
            initAnimes()
        }
        // Do an asynchronous operation to fetch users.
    }

    private suspend fun initAnimes() {
        var context = getApplication<Application>().applicationContext
        if (response.value != null){
            return
        }
        val result = withContext(Dispatchers.IO){
            repository.getSearchAnimes(tagid)
        }
        response.value = result
        if (response.value == null){
            toast(context,"获取信息失败，请检查网络！！")
            return
        }
        if (response.value!!.status == 0){
            animesList.value = response.value!!.data as ArrayList<AnimeData>?
        }
        debug(response.value.toString())
    }

    fun close(view : View){
        (view.context as Activity).finish()
    }
}