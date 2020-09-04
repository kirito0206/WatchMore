package com.example.watchmore.viewmodel

import android.app.Activity
import android.app.Application
import android.view.View
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.watchmore.model.bean.animebean.AnimeResponse
import com.example.watchmore.model.bean.animebean.Comment
import com.example.watchmore.model.network.repository.AnimeRepository
import com.example.watchmore.model.network.repository.UserRepository
import com.example.watchmore.util.SPUtils
import com.example.watchmore.util.debug
import com.example.watchmore.util.toast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AnimeDetailViewModel(application: Application) : AndroidViewModel(application) {

    var animeId : Int = 0
    val response = MutableLiveData<AnimeResponse>()
    val pic = MutableLiveData<String>()
    val title = MutableLiveData<String>()
    val describe = MutableLiveData<String>()
    val link = MutableLiveData<String>()
    val islike = MutableLiveData<Boolean>()
    val likeNum = MutableLiveData<String>()
    val sendContent = MutableLiveData<String>()
    val commentList = MutableLiveData<ArrayList<Comment>>()

    private val repository by lazy { AnimeRepository() }

    private suspend fun initAnime() {
        var context = getApplication<Application>().applicationContext
        if (response.value != null){
            return
        }
        val result = withContext(Dispatchers.IO){
            repository.getAnimeDetail(
                SPUtils.getData(context,
                    SPUtils.USER_FILE,
                    SPUtils.UserKey.USER_TOKEN,"") as String,animeId)
        }
        debug(animeId.toString())
        response.value = result
        if (response.value == null){
            toast(context,"获取信息失败，请检查网络！！")
            return
        }
        if (response.value!!.status == 0){
            pic.value = response.value!!.data.picture
            link.value = response.value!!.data.link
            describe.value = response.value!!.data.describe
            title.value = response.value!!.data.title
            islike.value = response.value!!.data.islike
            likeNum.value = "共有${response.value!!.data.likenum}人想看"
            commentList.value = response.value!!.data.comments as ArrayList<Comment>?
        }
        debug(response.value.toString())
    }

    private suspend fun addCommentRequest(comment: String) {
        var context = getApplication<Application>().applicationContext
        val result = withContext(Dispatchers.IO){
            repository.addAnimeComment(
                SPUtils.getData(context,
                    SPUtils.USER_FILE,
                    SPUtils.UserKey.USER_TOKEN,"") as String,animeId,comment)
        }
        debug(result.toString())
        if (result == null){
            toast(context,"评论失败，请检查网络！！")
            return
        }
        if (result.status == 0){
            toast(context,"评论成功！！")
            loadDatas()
        }
    }

    fun loadDatas() {
        GlobalScope.launch(Dispatchers.Main) {
            initAnime()
        }
        // Do an asynchronous operation to fetch users.
    }

    fun addComment(){
        var comment = sendContent.value
        if (comment == null || comment.equals(""))
            return
        sendContent.value = ""
        GlobalScope.launch(Dispatchers.Main) {
            addCommentRequest(comment)
        }
    }

    fun changeLikeAnime(){
        GlobalScope.launch(Dispatchers.Main) {
            changeLikeAnimeRequest()
        }
    }

    suspend fun changeLikeAnimeRequest(){
        var context = getApplication<Application>().applicationContext
        val result = withContext(Dispatchers.IO){
            if (islike.value!!)
                repository.deleteAnimeLike(
                    SPUtils.getData(context,
                        SPUtils.USER_FILE,
                        SPUtils.UserKey.USER_TOKEN,"") as String,animeId)
            else
                repository.addAnimeLike(
                    SPUtils.getData(context,
                        SPUtils.USER_FILE,
                        SPUtils.UserKey.USER_TOKEN,"") as String,animeId)
        }
        debug(result.toString())
        if (result == null){
            toast(context,"操作失败，请检查网络！！")
            return
        }
        if (result.status == 0){
            if (islike.value!!){
                toast(context,"取消赞成功！！")
                islike.value = false
                likeNum.value = "共有${response.value!!.data.likenum -1}人想看"
            }else{
                toast(context,"点赞成功！！")
                islike.value = true
                likeNum.value = "共有${response.value!!.data.likenum +1}人想看"
            }
        }
    }

    fun changeStarComment(comment: Comment){
        GlobalScope.launch(Dispatchers.Main) {
            changeStarCommentRequest(comment)
        }
    }

    suspend fun changeStarCommentRequest(comment: Comment){
        var context = getApplication<Application>().applicationContext
        val result = withContext(Dispatchers.IO){
            if (comment.islike)
                repository.deleteAnimeCommentStar(
                    SPUtils.getData(context,
                        SPUtils.USER_FILE,
                        SPUtils.UserKey.USER_TOKEN,"") as String,comment.commentid)
            else
                repository.addAnimeCommentStar(
                    SPUtils.getData(context,
                        SPUtils.USER_FILE,
                        SPUtils.UserKey.USER_TOKEN,"") as String,comment.commentid)
        }
        debug(result.toString())
        if (result == null){
            toast(context,"操作失败，请检查网络！！")
            return
        }
        if (result.status == 0){
            toast(context,"操作成功！！")
            loadDatas()
        }
    }

    fun finish(view:View){
        (view.context as Activity).finish()
    }
}