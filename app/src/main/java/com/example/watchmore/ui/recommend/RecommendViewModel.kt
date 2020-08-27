package com.example.watchmore.ui.recommend

import android.app.Application
import android.content.Intent
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.watchmore.model.bean.animebean.AnimeSearchResponse
import com.example.watchmore.model.bean.recommendbean.RecommendResponse
import com.example.watchmore.model.network.repository.AnimeRepository
import com.example.watchmore.model.network.repository.RecommendRepository
import com.example.watchmore.model.network.repository.UserRepository
import com.example.watchmore.ui.activity.EditActivity
import com.example.watchmore.ui.activity.MyRecommendActivity
import com.example.watchmore.util.debug
import com.example.watchmore.util.toast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RecommendViewModel(application: Application) : AndroidViewModel(application) {

    private val response = MutableLiveData<RecommendResponse>().also { loadDatas() }
    private val repository by lazy { RecommendRepository() }

    fun intentToMyRecommend(view : View){
        var intent = Intent(view.context, MyRecommendActivity::class.java)
        view.context?.startActivity(intent)
    }

    fun intentToEdit(view: View){
        var intent = Intent(view.context, EditActivity::class.java)
        intent.putExtra("editType",2)
        view.context?.startActivity(intent)
    }

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
            repository.getAllRecommend()
        }
        response.value = result
        if (response.value == null){
            toast(context,"获取信息失败，请检查网络！！")
            return
        }
        if (response.value!!.status == 0){

        }
        debug(response.value.toString())
    }
}