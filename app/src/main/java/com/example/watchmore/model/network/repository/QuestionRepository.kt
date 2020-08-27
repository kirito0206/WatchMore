package com.example.watchmore.model.network.repository

import com.example.watchmore.model.network.RetrofitHelper
import java.io.File

class QuestionRepository {
    var questionService = RetrofitHelper.questionService

    suspend fun getAllQuetions(page : Int) = questionService.getAllQuetions(page)

    suspend fun createQuestion(token: String, title: String, content: String, file: File?) = questionService.createQuestion(token,title,content,file)
}