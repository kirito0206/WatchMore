package com.example.watchmore.model.bean.questionbean

data class QuestionResponse(
    val count: Int,
    val `data`: List<QuestionBean>,
    val status: Int
)

data class QuestionBean(
    val authorid: Int,
    val authorname: String,
    val comment: List<QuestionComment>,
    val content: String,
    val dramaid: Int,
    val photos: List<String>,
    val time: String,
    val title: String
)

data class QuestionComment(
    val authorid: Int,
    val authorname: String,
    val commentid: Int,
    val content: String,
    val time: String
)