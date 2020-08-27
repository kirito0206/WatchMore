package com.example.watchmore.viewmodel

import android.app.Activity
import android.content.Context
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.watchmore.model.network.repository.QuestionRepository
import com.example.watchmore.model.network.repository.RecommendRepository
import com.example.watchmore.util.SPUtils
import com.example.watchmore.util.debug
import com.example.watchmore.util.toast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import me.iwf.photopicker.utils.PhotoPickerIntent


class EditViewModel : ViewModel() {

    var editType : Int = 1
    val REQUEST_CODE = 0
    var title = MutableLiveData<String>()
    var content = MutableLiveData<String>()
    var photos = MutableLiveData<ArrayList<String>>()
    private val repository0 by lazy { QuestionRepository() }
    private val repository1 by lazy { RecommendRepository() }

    fun update(view : View){
        when(editType){
            EditType.EDIT_QUESTION -> {
                GlobalScope.launch(Dispatchers.Main) {
                    addQuestion(view.context)
                }
                close(view)
            }
            EditType.EDIT_RECOMMEND -> {
                close(view)
            }
        }
    }

    suspend fun addQuestion(context : Context){
        val result = withContext(Dispatchers.IO){
            repository0.createQuestion(SPUtils.getData(context,SPUtils.USER_FILE,SPUtils.UserKey.USER_TOKEN,"") as String
                ,title.value!!,content.value!!,null)
        }
        if (result == null){
            toast(context,"创建失败，请重试！！")
            return
        }
        if (result.status == 0){
            toast(context,"创建问番成功！！")
        }
        debug(result.toString())
    }

    /*suspend fun addRecommend(context : Context){
        val result = withContext(Dispatchers.IO){
            repository1.createRecommend(SPUtils.getData(context,SPUtils.USER_FILE,SPUtils.UserKey.USER_TOKEN,"") as String
                ,title.value!!,content.value!!,null)
        }
        if (result == null){
            toast(context,"创建失败，请重试！！")
            return
        }
        if (result.status == 0){
            toast(context,"成功推荐番剧！！")
        }
        debug(result.toString())
    }*/

    fun close(view: View) = (view.context as Activity).finish()

    object EditType{
        const val EDIT_QUESTION = 1
        const val EDIT_RECOMMEND = 2
    }

    fun pickPhotos(view: View){
        val intent = PhotoPickerIntent(view.context as Activity)
        intent.setPhotoCount(9)
        intent.setShowCamera(true)
        (view.context as Activity).startActivityForResult(intent, REQUEST_CODE)
    }


}