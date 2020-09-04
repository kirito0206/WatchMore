package com.example.watchmore.model.network.services

import com.example.watchmore.model.bean.CommonResponse
import com.example.watchmore.model.bean.questionbean.QuestionResponse
import com.example.watchmore.model.bean.recommendbean.RecommendResponse
import okhttp3.MultipartBody
import retrofit2.http.*

interface RecommendService {

    @FormUrlEncoded
    @POST("recommend/get")
    suspend fun getAllRecommend(@Field("page") page : Int) : RecommendResponse

    @FormUrlEncoded
    @POST("recommend/get")
    suspend fun getRecommendDetail(@Field("dramaid") dramaid : Int) : RecommendResponse

    @Multipart
    @POST("recommend")
    suspend fun createRecommend(@Part parts : List<MultipartBody.Part>) : CommonResponse

    @POST("recommend/comment")
    suspend fun createRecommendComment() : CommonResponse

    @POST("recommend/star")
    suspend fun starRecommend() : CommonResponse

    @POST("recommend/collect")
    suspend fun collectRecommend() : CommonResponse

    @DELETE("recommend")
    suspend fun deleteRecommend() : CommonResponse

    @POST("recommend/comment")
    suspend fun atUser() : CommonResponse
}