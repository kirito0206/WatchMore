package com.example.watchmore.viewmodel

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.provider.MediaStore
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.watchmore.model.network.repository.RecommendRepository
import com.example.watchmore.util.SPUtils
import com.example.watchmore.util.debug
import com.example.watchmore.util.toast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

class RecommendEditViewModel : ViewModel(){

    val REQUEST_CODE = 0
    var title = MutableLiveData<String>()
    var content = MutableLiveData<String>()
    var photos = MutableLiveData<ArrayList<String>>()
    var animeTitle = MutableLiveData<String>()
    var animeDescribe = MutableLiveData<String>()
    var animePics = MutableLiveData<ArrayList<String>>()
    var flag = 0

    private val repository0 by lazy { RecommendRepository() }

    fun update(view : View){
        if (title.value == null || content.value == null){
            toast(view.context,"请全部填写！！")
            return
        }
        GlobalScope.launch(Dispatchers.Main) {
            addRecommend(view.context)
        }
        close(view)
    }

    suspend fun addRecommend(context : Context){
        var body = filesToMultipartBodyParts(context)
        val result = withContext(Dispatchers.IO){
            repository0.createRecommend(body!!)
        }
        if (result == null){
            toast(context,"创建失败，请重试！！")
            return
        }
        if (result.status == 0){
            toast(context,"推荐番剧成功！！")
        }
        debug(result.toString())
    }

    fun close(view: View) = (view.context as Activity).finish()

    fun pickPhotos(view: View,flag0 : Int){
        if (ContextCompat.checkSelfPermission(view.context, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            //未授权，申请授权(从相册选择图片需要读取存储卡的权限)
            ActivityCompat.requestPermissions(view.context as Activity, (arrayOf(
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE)), REQUEST_CODE)
        } else {
            //已授权，获取照片
            flag = flag0
            choosePhoto(view.context)
        }
    }

    fun filesToMultipartBodyParts(context: Context): List<MultipartBody.Part>? {
        val parts: MutableList<MultipartBody.Part> = ArrayList((photos.value?.size ?: 0)+ (animePics.value?.size?:0) +5)
        var token = SPUtils.getData(context, SPUtils.USER_FILE, SPUtils.UserKey.USER_TOKEN,"") as String
        parts.add(MultipartBody.Part.createFormData("title", title.value))
        parts.add(MultipartBody.Part.createFormData("token",token))
        parts.add(MultipartBody.Part.createFormData("content",content.value))
        parts.add(MultipartBody.Part.createFormData("animetitle",animeTitle.value))
        parts.add(MultipartBody.Part.createFormData("describe",animeDescribe.value))
        if (photos.value != null) {
            for (arr in photos.value!!) {
                // TODO: 16-4-2  这里为了简单起见，没有判断file的类型
                var file = File(arr)
                debug(arr)
                val requestBody =
                    RequestBody.create(MediaType.parse("image/*"), file)
                val part =
                    MultipartBody.Part.createFormData("photo", file?.name, requestBody)
                debug(file.name)
                parts.add(part)
            }
        }
        if (animePics.value != null) {
            for (arr in animePics.value!!) {
                // TODO: 16-4-2  这里为了简单起见，没有判断file的类型
                var file = File(arr)
                debug(arr)
                val requestBody =
                    RequestBody.create(MediaType.parse("image/*"), file)
                val part =
                    MultipartBody.Part.createFormData("animepicture", file?.name, requestBody)
                debug(file.name)
                parts.add(part)
            }
        }
        return parts
    }

    fun choosePhoto(context : Context){
        val intentToPickPic = Intent(Intent.ACTION_PICK, null)
        intentToPickPic.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*")
        (context as Activity).startActivityForResult(intentToPickPic, REQUEST_CODE)
    }
}