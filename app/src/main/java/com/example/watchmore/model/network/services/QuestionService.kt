package com.example.watchmore.model.network.services

import com.example.watchmore.model.bean.CommonResponse
import com.example.watchmore.model.bean.questionbean.QuestionResponse
import okhttp3.MultipartBody
import retrofit2.http.*
import java.io.File

interface QuestionService {

    @FormUrlEncoded
    @POST("ask/get")
    suspend fun getAllQuetions(@Field("page") page : Int) : QuestionResponse

    @FormUrlEncoded
    @POST("ask/get")
    suspend fun getQuestionDetail(@Field("dramaid") dramaid:Int) : QuestionResponse

    @Multipart
    @POST("ask")
    suspend fun createQuestion(@Part parts : List<MultipartBody.Part>) : CommonResponse

    @POST("recommend/comment")
    suspend fun createQuestionComment() : CommonResponse

    @POST("recommend/star")
    suspend fun starQuestion() : CommonResponse

    @POST("recommend/solve")
    suspend fun solveQuestion() : CommonResponse

    @DELETE("recommend/comment")
    suspend fun deleteQuestionComment() : CommonResponse

    @DELETE("recommend/star")
    suspend fun deleteQuestionStar() : CommonResponse

    @DELETE("ask/solve")
    suspend fun recoverQuestion() : CommonResponse

    @POST("recommend/comment")
    suspend fun atUser() : CommonResponse
}