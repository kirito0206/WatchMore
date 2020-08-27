package com.example.watchmore.viewmodel

import android.content.Context
import android.content.Intent
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.watchmore.MainActivity
import com.example.watchmore.model.bean.CommonResponse
import com.example.watchmore.model.network.repository.UserRepository
import com.example.watchmore.ui.activity.LoginActivity
import com.example.watchmore.util.SPUtils
import com.example.watchmore.util.debug
import com.example.watchmore.util.toast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RegisterViewModel : ViewModel() {

    var loginAccount = MutableLiveData<String>()
    var loginPasswd = MutableLiveData<String>()
    var loginRepeatpwd = MutableLiveData<String>()
    var loginEmail = MutableLiveData<String>()
    val response : MutableLiveData<CommonResponse> by lazy{
        MutableLiveData<CommonResponse>()
    }

    private val repository by lazy { UserRepository() }

    private suspend fun createUser(context: Context) {
        if (loginAccount.value == null || loginAccount.value.equals("")){
            toast(context,"用户名不能为空！！")
            return
        }
        if (loginPasswd.value == null || loginPasswd.value.equals("")){
            toast(context,"密码不能为空！！")
            return
        }
        if (loginRepeatpwd.value == null || loginRepeatpwd.value.equals("")){
            toast(context,"确认密码不能为空！！")
            return
        }
        if (loginEmail.value == null || loginEmail.value.equals("")){
            toast(context,"邮箱不能为空！！")
            return
        }
        val result = withContext(Dispatchers.IO){
            repository.createUser(loginAccount.value!!,loginPasswd.value!!,loginRepeatpwd.value!!,loginEmail.value!!)
        }
        response.value = result
        if (response.value == null){
            toast(context,"注册失败，请重试！！")
            return
        }
        if (response.value!!.status == 0){
            SPUtils.putData(context, SPUtils.USER_FILE, SPUtils.UserKey.USER_ACCOUNT,loginAccount.value!!)
            SPUtils.putData(context, SPUtils.USER_FILE, SPUtils.UserKey.USER_PASSWORD,loginPasswd.value!!)
            SPUtils.putData(context, SPUtils.USER_FILE, SPUtils.UserKey.USER_TOKEN,response.value!!.data.token)
            intentToMainActivity(context)
        }
        debug(response.value.toString())
    }

    fun loadDatas(view : View) {
        GlobalScope.launch(Dispatchers.Main) {
            createUser(view.context)
        }
        // Do an asynchronous operation to fetch users.
    }

    fun intentToMainActivity(context: Context){
        var intent = Intent(context, MainActivity::class.java)
        context.startActivity(intent)
    }

    fun intentToLogin(view: View){
        var intent = Intent(view.context, LoginActivity::class.java)
        view.context.startActivity(intent)
    }
}