package com.example.watchmore.viewmodel

import android.app.Application
import android.content.Context
import android.content.Intent
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.watchmore.MainActivity
import com.example.watchmore.model.bean.CommonResponse
import com.example.watchmore.model.network.repository.UserRepository
import com.example.watchmore.ui.activity.RegisterActivity
import com.example.watchmore.util.SPUtils
import com.example.watchmore.util.debug
import com.example.watchmore.util.toast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    var loginAccount = MutableLiveData<String>().apply {
        val context = getApplication<Application>().baseContext
        value = SPUtils.getData(context,SPUtils.USER_FILE,SPUtils.UserKey.USER_ACCOUNT,"") as String
    }
    var loginPasswd = MutableLiveData<String>().apply {
        val context = getApplication<Application>().baseContext
        value = SPUtils.getData(context,SPUtils.USER_FILE,SPUtils.UserKey.USER_PASSWORD,"") as String
    }
    val response : MutableLiveData<CommonResponse> by lazy{
        MutableLiveData<CommonResponse>()
    }
    private val repository by lazy { UserRepository() }

    private suspend fun loginUser(context: Context) {
        if (loginAccount.value == null || loginAccount.value.equals("")){
            toast(context,"用户名不能为空！！")
            return
        }
        if (loginPasswd.value == null || loginPasswd.value.equals("")){
            toast(context,"密码不能为空！！")
            return
        }
        val result = withContext(Dispatchers.IO){
            repository.login(loginAccount.value!!,loginPasswd.value!!)
        }
        response.value = result
        if (response.value == null){
            toast(context,"登陆失败，请重试！！")
            return
        }
        if (response.value!!.status == 0){
            SPUtils.putData(context,SPUtils.USER_FILE,SPUtils.UserKey.USER_ACCOUNT,loginAccount.value!!)
            SPUtils.putData(context,SPUtils.USER_FILE,SPUtils.UserKey.USER_PASSWORD,loginPasswd.value!!)
            SPUtils.putData(context,SPUtils.USER_FILE,SPUtils.UserKey.USER_TOKEN,response.value!!.data.token)
            intentToMainActivity(context)
        }
        debug(response.value.toString())
    }

    fun loadDatas(view : View) {
        GlobalScope.launch(Dispatchers.Main) {
            loginUser(view.context)
        }
        // Do an asynchronous operation to fetch users.
    }

    fun intentToMainActivity(context: Context){
        var intent = Intent(context, MainActivity::class.java)
        context.startActivity(intent)
    }

    fun intentToRegister(view: View){
        var intent = Intent(view.context, RegisterActivity::class.java)
        view.context.startActivity(intent)
    }
}