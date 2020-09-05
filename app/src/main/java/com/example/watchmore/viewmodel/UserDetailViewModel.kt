package com.example.watchmore.viewmodel

import android.Manifest
import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.provider.MediaStore
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.watchmore.model.network.repository.UserRepository
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

class UserDetailViewModel(application: Application) : AndroidViewModel(application) {

    var userAvatar = MutableLiveData<String>()
    var userName = MutableLiveData<String>()
    var userEmail = MutableLiveData<String>()
    var password = MutableLiveData<String>().apply {
        var context = getApplication<Application>().applicationContext
        value = SPUtils.getData(context,SPUtils.USER_FILE,SPUtils.UserKey.USER_PASSWORD,"") as String
    }

    private val repository by lazy { UserRepository() }

    private suspend fun updateUserDetail(context: Context) {
        val result = withContext(Dispatchers.IO){
            var parts = filesToMultipartBodyParts(context,userAvatar.value)
            repository.updateUserDetaile(parts!!)
        }
        if (result == null){
            toast(context,"更新失败，请重试！！")
            return
        }
        debug(result.toString())
        if (result.status == 0){
            SPUtils.putData(context, SPUtils.USER_FILE, SPUtils.UserKey.USER_PASSWORD,password.value!!)
            SPUtils.putData(context, SPUtils.USER_FILE, SPUtils.UserKey.USER_TOKEN,result.data.token)
            toast(context,"修改信息成功！！")
            (context as Activity).finish()
        }
    }

    fun update(view : View){
        GlobalScope.launch(Dispatchers.Main) {
            updateUserDetail(view.context)
        }
    }

    fun pickPhotos(view: View){
        if (ContextCompat.checkSelfPermission(view.context, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            //未授权，申请授权(从相册选择图片需要读取存储卡的权限)
            ActivityCompat.requestPermissions(view.context as Activity, (arrayOf(
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE)), 0)
        } else {
            //已授权，获取照片
            choosePhoto(view.context)
        }
    }

    fun filesToMultipartBodyParts(context: Context,avatar: String?): List<MultipartBody.Part> {
        val parts: MutableList<MultipartBody.Part> = ArrayList(5)
        var token = SPUtils.getData(context,SPUtils.USER_FILE,SPUtils.UserKey.USER_TOKEN,"") as String
        parts.add(MultipartBody.Part.createFormData("name", userName.value))
        parts.add(MultipartBody.Part.createFormData("token",token))
        parts.add(MultipartBody.Part.createFormData("email",userEmail.value))
        parts.add(MultipartBody.Part.createFormData("password",password.value))
        if (avatar != null && !avatar.contains("http")) {
            var file = File(avatar)
            val requestBody =
                RequestBody.create(MediaType.parse("image/*"), file)
            val part =
                MultipartBody.Part.createFormData("avatar", file?.name, requestBody)
            debug(file.name)
            parts.add(part)
        }
        return parts
    }

    fun choosePhoto(context : Context){
        val intentToPickPic = Intent(Intent.ACTION_PICK, null)
        intentToPickPic.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*")
        (context as Activity).startActivityForResult(intentToPickPic, 0)
    }

    fun close(view: View) = (view.context as Activity).finish()
}