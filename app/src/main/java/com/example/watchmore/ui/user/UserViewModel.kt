package com.example.watchmore.ui.user

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.Intent
import android.view.View
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.watchmore.R
import com.example.watchmore.model.bean.userbean.PersonResponse
import com.example.watchmore.model.network.repository.UserRepository
import com.example.watchmore.ui.activity.MyQuestionActivity
import com.example.watchmore.ui.activity.MyRecommendActivity
import com.example.watchmore.ui.activity.ThemeActivity
import com.example.watchmore.ui.activity.UserDetailActivity
import com.example.watchmore.util.SPUtils
import com.example.watchmore.util.debug
import com.example.watchmore.util.toast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class UserViewModel(application: Application) : AndroidViewModel(application){

    val response = MutableLiveData<PersonResponse>().also { loadDatas() }
    val userName = MutableLiveData<String>()
    val headImage = MutableLiveData<String>()
    val followers = MutableLiveData<String>()
    val fans = MutableLiveData<String>()
    val collects = MutableLiveData<String>()
    val recommend = MutableLiveData<String>()
    val question = MutableLiveData<String>()
    private val repository by lazy { UserRepository() }

    private suspend fun initUser() {
        var context = getApplication<Application>().applicationContext
        if (response.value != null){
            return
        }
        val result = withContext(Dispatchers.IO){
            repository.getPersonDetail(SPUtils.getData(context,SPUtils.USER_FILE,SPUtils.UserKey.USER_TOKEN,"") as String)
        }
        response.value = result
        if (response.value == null){
            toast(context,"获取信息失败，请检查网络！！")
            return
        }
        if (response.value!!.status == 0){
            userName.value = response.value!!.data.name
            headImage.value = response.value!!.data.avatar
            followers.value = response.value!!.data.followers.toString()
            fans.value = response.value!!.data.fans.toString()
            collects.value = response.value!!.data.collects.toString()
            recommend.value = "我的推荐 ${response.value!!.data.Rdramas.toString()} "
            question.value = "我的问番 ${response.value!!.data.Adramas.toString()} "
            SPUtils.putData(context,SPUtils.USER_FILE,SPUtils.UserKey.USER_USERID,response.value!!.data.userid)
        }
        debug(response.value.toString())
    }

    fun loadDatas() {
        GlobalScope.launch(Dispatchers.Main) {
            initUser()
        }
        // Do an asynchronous operation to fetch users.
    }

    fun intentToTheme(context : Context){
        var intent = Intent(context, ThemeActivity::class.java)
        (context as Activity).startActivityForResult(intent,1)
    }

    fun intentToUserDetail(view: View){
        var intent = Intent(view.context, UserDetailActivity::class.java)
        intent.putExtra("userAvatar",response.value!!.data.avatar)
        intent.putExtra("userName",response.value!!.data.name)
        intent.putExtra("userEmail",response.value!!.data.email)
        view.context?.startActivity(intent)
    }

    fun logout(context : Context){

    }

    fun intentToFollowers(view: View){
        var intent = Intent(view.context, UserDetailActivity::class.java)
        view.context?.startActivity(intent)
    }

    fun intentToFans(view: View){
        var intent = Intent(view.context, UserDetailActivity::class.java)
        view.context?.startActivity(intent)
    }

    fun intentToCollect(view: View){
        var intent = Intent(view.context, UserDetailActivity::class.java)
        view.context?.startActivity(intent)
    }

    fun intentToRecommend(view: View){
        var intent = Intent(view.context, MyRecommendActivity::class.java)
        view.context?.startActivity(intent)
    }

    fun intentToQuestion(view: View){
        var intent = Intent(view.context, MyQuestionActivity::class.java)
        view.context?.startActivity(intent)
    }
}
