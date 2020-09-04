package com.example.watchmore.viewmodel

import android.app.Activity
import android.app.Application
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.watchmore.model.bean.questionbean.QuestionBean
import com.example.watchmore.model.bean.userbean.AccompanyAnime
import com.example.watchmore.model.bean.userbean.AccompanyAnimeResponse
import com.example.watchmore.model.bean.userbean.UserBean
import com.example.watchmore.model.network.repository.UserRepository
import com.example.watchmore.util.SPUtils
import com.example.watchmore.util.debug
import com.example.watchmore.util.toast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.properties.Delegates

class MyQuestionViewModel(application: Application) : AndroidViewModel(application) {

    val response = MutableLiveData<AccompanyAnimeResponse>().also { loadDatas() }
    private val repository by lazy { UserRepository() }
    val questionsList = MutableLiveData<ArrayList<QuestionBean>>()
    val usersList = MutableLiveData<ArrayList<UserBean>>()
    private val userRepository by lazy { UserRepository() }
    var userid : Int? = null

    private suspend fun initQuestion() {
        var context = getApplication<Application>().applicationContext
        if (response.value != null){
            return
        }
        val result = withContext(Dispatchers.IO){
            repository.getUserQuestion(
                SPUtils.getData(context,
                    SPUtils.USER_FILE,
                    SPUtils.UserKey.USER_TOKEN,"") as String,userid)
        }
        response.value = result
        if (response.value == null){
            toast(context,"获取信息失败，请检查网络！！")
            return
        }
        if (response.value!!.status == 0){
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
        val usersListTest = ArrayList<UserBean>()
        GlobalScope.launch(Dispatchers.Main) {
            var user : UserBean? = null
            if (userid == null)
                userid = getPersonId()

            user = userid?.let { getUser(it) }
            for (i in 1..response.value!!.data.size){
                if (user != null) {
                    usersListTest.add(user)
                }
            }
            usersList.value = usersListTest
            questionsList.value = changeToQuestionList(response.value!!.data!!,usersListTest)
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

    suspend fun getPersonId() : Int?{
        var context = getApplication<Application>().applicationContext
        val result = withContext(Dispatchers.IO){
            userRepository.getPersonDetail(SPUtils.getData(context,SPUtils.USER_FILE,SPUtils.UserKey.USER_TOKEN,"") as String)
        }
        if (result == null){
            toast(context,"获取信息失败，请检查网络！！")
            return null
        }
        debug(result.toString())
        return if (result.status == 0){
            SPUtils.putData(context,SPUtils.USER_FILE,SPUtils.UserKey.USER_USERID,result.data.userid)
            result.data.userid
        }else{
            toast(context,result.message)
            null
        }
    }

    fun changeToQuestionList(list1 : List<AccompanyAnime>,list2 : List<UserBean>): ArrayList<QuestionBean> {
        var list0 = ArrayList<QuestionBean>()
        for (i in 0 until list1.size){
            var t = list1[i]
            var user = list2[i]
            list0.add(QuestionBean(user.userid,user.name,null,null,t.dramaid,t.photo,null,t.title))
        }
        return list0
    }

    fun close(view : View){
        (view.context as Activity).finish()
    }
}