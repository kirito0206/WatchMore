package com.example.watchmore.viewmodel

import android.app.Activity
import android.app.Application
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.watchmore.model.bean.animebean.Comment
import com.example.watchmore.model.bean.questionbean.QuestionResponse
import com.example.watchmore.model.bean.userbean.UserBean
import com.example.watchmore.model.network.repository.QuestionRepository
import com.example.watchmore.model.network.repository.UserRepository
import com.example.watchmore.util.debug
import com.example.watchmore.util.toast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class QuestionDetaiViewModel(application: Application) : AndroidViewModel(application) {

    val response = MutableLiveData<QuestionResponse>()
    var name = MutableLiveData<String>()
    var title = MutableLiveData<String>()
    var time = MutableLiveData<String>()
    var content = MutableLiveData<String>()
    var photos = MutableLiveData<ArrayList<String>>()
    var comments = MutableLiveData<ArrayList<Comment>>()
    var user = MutableLiveData<UserBean>()
    var dramaid = 0
    private val questionRepository by lazy { QuestionRepository() }
    private val userRepository by lazy { UserRepository() }

    private suspend fun initQuestion() {
        var context = getApplication<Application>().applicationContext
        if (response.value != null){
            return
        }
        val result = withContext(Dispatchers.IO){
            questionRepository.getQuestionDetail(dramaid)
        }
        response.value = result
        debug(result.toString())
        if (response.value == null){
            toast(context,"获取信息失败，请检查网络！！")
            return
        }
        if (response.value!!.status == 0){
            name.value = response.value!!.data[0].authorname
            time.value = response.value!!.data[0].time
            title.value = response.value!!.data[0].title
            content.value = response.value!!.data[0].content
            photos.value = response.value!!.data[0].photos as ArrayList<String>
            comments.value = response.value!!.data[0].comment as ArrayList<Comment>
            initUsers()
        }
        debug(response.value.toString())
    }

    fun loadDatas() {
        GlobalScope.launch(Dispatchers.Main) {
            initQuestion()
        }
        // Do an asynchronous operation to fetch users.
    }

    fun initUsers(){
        GlobalScope.launch(Dispatchers.Main) {
            var user0 = response.value!!.data[0].authorid?.let { getUser(it) }
            user.value = user0
        }
    }

    suspend fun getUser(userid : Int) : UserBean?{
        var context = getApplication<Application>().applicationContext
        val result = withContext(Dispatchers.IO){
            userRepository.getUserDetail(userid)
        }
        if (result == null){
            toast(context,"获取信息失败，请检查网络！！")
            return null
        }
        return if (result.status == 0){
            result.data[0]
        }else{
            toast(context,result.message)
            null
        }
    }

    fun close(view:View){
        (view.context as Activity).finish()
    }
}