package com.example.watchmore.ui.question

import android.app.Application
import android.content.Intent
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.watchmore.model.bean.questionbean.QuestionBean
import com.example.watchmore.model.bean.questionbean.QuestionResponse
import com.example.watchmore.model.bean.userbean.UserBean
import com.example.watchmore.model.network.repository.QuestionRepository
import com.example.watchmore.model.network.repository.UserRepository
import com.example.watchmore.ui.activity.QuestionEditActivity
import com.example.watchmore.ui.activity.MyQuestionActivity
import com.example.watchmore.util.debug
import com.example.watchmore.util.toast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class QuestionViewModel(application: Application) : AndroidViewModel(application) {

    private val response = MutableLiveData<QuestionResponse>().also { loadDatas() }
    private val questionRepository by lazy { QuestionRepository() }
    private val userRepository by lazy { UserRepository() }
    var page = 1
    val questionsList = MutableLiveData<ArrayList<QuestionBean>>()
    val usersList = MutableLiveData<ArrayList<UserBean>>()

    fun intentToMyQuestion(view : View){
        var intent = Intent(view.context, MyQuestionActivity::class.java)
        view.context?.startActivity(intent)
    }

    fun intentToEdit(view: View){
        var intent = Intent(view.context, QuestionEditActivity::class.java)
        view.context?.startActivity(intent)
    }

    fun loadDatas() {
        GlobalScope.launch(Dispatchers.Main) {
            initQuestions()
        }
        // Do an asynchronous operation to fetch users.
    }

    private suspend fun initQuestions() {
        var context = getApplication<Application>().applicationContext
        val result = withContext(Dispatchers.IO){
            questionRepository.getAllQuetions(page)
        }
        response.value = result
        if (response.value == null){
            return
        }
        if (response.value!!.status == 0){
            initUsers()
        }else
            page = 0
        debug(response.value.toString())
    }

    fun initUsers(){
        val usersListTest = ArrayList<UserBean>()

        GlobalScope.launch(Dispatchers.Main) {
            for (question in response.value!!.data){
                question.authorid?.let { getUser(it)?.let { usersListTest.add(it) } }
            }
            usersList.value = usersListTest
            questionsList.value = response.value!!.data as ArrayList<QuestionBean>
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
}