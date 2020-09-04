package com.example.watchmore.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.watchmore.model.bean.userbean.AccompanyAnime
import com.example.watchmore.model.bean.userbean.AccompanyAnimeResponse
import com.example.watchmore.model.network.repository.UserRepository
import com.example.watchmore.util.SPUtils
import com.example.watchmore.util.debug
import com.example.watchmore.util.toast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MyRecommendViewModel(application: Application) : AndroidViewModel(application) {

    val response = MutableLiveData<AccompanyAnimeResponse>().also { loadDatas() }
    private val repository by lazy { UserRepository() }
    val recommendList = MutableLiveData<ArrayList<AccompanyAnime>>()

    private suspend fun initRecommend() {
        var context = getApplication<Application>().applicationContext
        if (response.value != null){
            return
        }
        val result = withContext(Dispatchers.IO){
            repository.getUserRecommend(
                SPUtils.getData(context,
                    SPUtils.USER_FILE,
                    SPUtils.UserKey.USER_TOKEN,"") as String,null)
        }
        response.value = result
        if (response.value == null){
            toast(context,"获取信息失败，请检查网络！！")
            return
        }
        if (response.value!!.status == 0){
            recommendList.value = response.value!!.data as ArrayList<AccompanyAnime>?
        }
        debug(response.value.toString())
    }

    fun loadDatas() {
        GlobalScope.launch(Dispatchers.Main) {
            initRecommend()
        }
        // Do an asynchronous operation to fetch users.
    }
}