package com.example.watchmore.model.network.repository

import com.example.watchmore.model.network.RetrofitHelper
import okhttp3.MultipartBody
import java.io.File

class QuestionRepository {
    var questionService = RetrofitHelper.questionService

    suspend fun getAllQuetions(page : Int) = questionService.getAllQuetions(page)

    suspend fun createQuestion(parts : List<MultipartBody.Part>) = questionService.createQuestion(parts)

    suspend fun getQuestionDetail(dramaid : Int) = questionService.getQuestionDetail(dramaid)
}