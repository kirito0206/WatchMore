package com.example.watchmore.model.network.services

import com.example.watchmore.model.bean.CommonResponse
import com.example.watchmore.model.bean.questionbean.QuestionResponse
import com.example.watchmore.model.bean.recommendbean.RecommendResponse
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST

interface RecommendService {

    @GET("recommend/get")
    suspend fun getAllRecommend() : RecommendResponse

    @GET("recommend/get")
    suspend fun getSingleRecommend() : RecommendResponse

    @POST("recommend")
    suspend fun createRecommend() : CommonResponse

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