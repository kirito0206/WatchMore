package com.example.watchmore.model.network.repository

import com.example.watchmore.model.bean.userbean.PersonResponse
import com.example.watchmore.model.network.RetrofitHelper
import okhttp3.MediaType
import okhttp3.RequestBody

class UserRepository {
    var userService = RetrofitHelper.userService
    suspend fun createUser(name : String,password : String,repeatpd : String,email : String) = userService.createUser(name, password, repeatpd, email)

    suspend fun login(name : String,password : String) = userService.login(name, password)

    suspend fun logout(token : String) = userService.logout(token)

    suspend fun getPersonDetail(token : String) : PersonResponse = userService.getPersonDetail(token)

    suspend fun addFollows(token : String,userid : Int) = userService.addFollows(token, userid)

    suspend fun deleteFollows(token : String,userid : Int) = userService.deleteFollows(token, userid)

    suspend fun getUserDetail(userid: Int) = userService.getUserDetail(userid)

    suspend fun getUserQuestion(token: String,userid: Int?) = userService.getQuestions(token,userid)

    suspend fun getUserRecommend(token: String,userid: Int?) = userService.getRecommends(token,userid)
}