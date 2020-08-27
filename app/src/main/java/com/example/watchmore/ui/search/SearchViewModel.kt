package com.example.watchmore.ui.search

import android.app.Application
import android.content.Intent
import android.view.View
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.watchmore.model.SearchModel
import com.example.watchmore.model.bean.animebean.AnimeBean
import com.example.watchmore.model.bean.animebean.AnimeData
import com.example.watchmore.model.bean.animebean.AnimeSearchResponse
import com.example.watchmore.model.network.repository.AnimeRepository
import com.example.watchmore.ui.activity.MainSearchActivity
import com.example.watchmore.util.debug
import com.example.watchmore.util.toast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*
import java.util.Collections.copy
import kotlin.collections.ArrayList

class SearchViewModel(application: Application) : AndroidViewModel(application) {

    private val response = MutableLiveData<AnimeSearchResponse>().also { loadDatas() }
    private val repository by lazy { AnimeRepository() }
    private val searchModel by lazy { SearchModel() }
    val animesList = MutableLiveData<ArrayList<AnimeData>>()

    fun intentToMainSearch(view : View){
        var intent = Intent(view.context, MainSearchActivity::class.java)
        view.context.startActivity(intent)
    }

    fun getImageList() = searchModel.imageList

    fun getTimeTagMap() = searchModel.timeTagMap

    fun getStyleTagMap() = searchModel.styleTagMap

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
            repository.getAllAnimes()
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
}