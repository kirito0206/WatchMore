package com.example.watchmore.viewmodel

import android.content.Context
import android.content.Intent
import android.view.View
import androidx.lifecycle.ViewModel
import com.example.watchmore.model.network.repository.QuestionRepository
import com.example.watchmore.model.network.repository.UserRepository
import com.example.watchmore.ui.activity.AnimeDetailActivity
import com.example.watchmore.ui.activity.QuestionDetailActivity
import com.example.watchmore.ui.activity.RecommendDetailActivity
import com.example.watchmore.util.SPUtils
import com.example.watchmore.util.debug
import com.example.watchmore.util.toast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CommonViewModel : ViewModel() {

    val repository by lazy { UserRepository() }

    fun intentToAnimeDetail(view : View,animeId : Int){
        var intent = Intent(view.context, AnimeDetailActivity::class.java)
        intent.putExtra("animeId",animeId)
        view.context.startActivity(intent)
    }

    fun intentToQuestionDetail(view : View,dramaid : Int){
        var intent = Intent(view.context, QuestionDetailActivity::class.java)
        intent.putExtra("dramaid",dramaid)
        view.context.startActivity(intent)
    }

    fun intentToRecommendDetail(view : View,dramaid : Int){
        var intent = Intent(view.context, RecommendDetailActivity::class.java)
        intent.putExtra("dramaid",dramaid)
        view.context.startActivity(intent)
    }

    fun delete(view: View,dramaid: Int) {
        GlobalScope.launch(Dispatchers.Main) {
            deleteInfo(view.context,dramaid)
        }
    }

    suspend fun deleteInfo(context: Context, dramaid : Int){
        val result = withContext(Dispatchers.IO){
            repository.deleteQuestion(
                SPUtils.getData(context,
                    SPUtils.USER_FILE,
                    SPUtils.UserKey.USER_TOKEN,"") as String,dramaid)
        }
        if (result == null){
            toast(context,"获取信息失败，请检查网络！！")
            return
        }
        if (result.status == 0){
            toast(context,"删除成功！！")
        }
        debug(result.toString())
    }

}