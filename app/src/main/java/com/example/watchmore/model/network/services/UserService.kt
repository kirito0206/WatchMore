package com.example.watchmore.model.network.services

import com.example.watchmore.model.bean.CommonResponse
import com.example.watchmore.model.bean.userbean.*
import okhttp3.MultipartBody
import retrofit2.http.*
import java.io.File

interface UserService {
    @FormUrlEncoded
    @POST("create")
    suspend fun createUser(@Field("name") name : String, @Field("password") password : String, @Field("repeatpd") repeatpd : String, @Field("email") email : String) : CommonResponse

    @FormUrlEncoded
    @POST("login")
    suspend fun login(@Field("name") name : String,@Field("password") password : String) : CommonResponse

    @FormUrlEncoded
    @POST("logout")
    suspend fun logout(@Field("token") token : String) : CommonResponse

    @FormUrlEncoded
    @POST("person")
    suspend fun getPersonDetail(@Field("token") token : String) : PersonResponse

    @FormUrlEncoded
    @POST("follows")
    suspend fun addFollows(@Field("token") token : String,@Field("userid") userid : Int) : CommonResponse

    @FormUrlEncoded
    @DELETE("follows")
    suspend fun deleteFollows(@Field("token") token : String,@Field("userid") userid : Int) : CommonResponse

    @FormUrlEncoded
    @POST("get")
    suspend fun getUserDetail(@Field("userid") userid : Int) : UserResponse

    @GET("get")
    suspend fun searchUsers(@Query("ask") ask : String) : UserResponse

    @Multipart
    @POST("update")
    suspend fun updateUserDetail(@Part parts : List<MultipartBody.Part>) : CommonResponse

    @POST("search")
    suspend fun findPassword(@Query("email") email : String) : CommonResponse

    @POST("person/fans")
    suspend fun getFans(@Query("token") token: String,@Query("userid") userid: Int) : FollowResponse

    @POST("person/follower")
    suspend fun getFollower(@Query("token") token: String,@Query("userid") userid: Int) : FollowResponse

    @FormUrlEncoded
    @POST("person/recommend")
    suspend fun getRecommends(@Field("token") token: String,@Field("userid") userid: Int?) : AccompanyAnimeResponse

    @FormUrlEncoded
    @POST("person/ask")
    suspend fun getQuestions(@Field("token") token: String,@Field("userid") userid: Int?) : AccompanyAnimeResponse

    @Headers("Content-Type:application/form-data")
    @HTTP(method = "DELETE", path = "user/ask", hasBody = true)
    @FormUrlEncoded
    suspend fun deleteQuestion(@Field("token") token : String, @Field("dramaid") dramaid : Int) : CommonResponse
}