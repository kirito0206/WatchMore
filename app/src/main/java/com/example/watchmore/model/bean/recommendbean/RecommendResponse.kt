package com.example.watchmore.model.bean.recommendbean

data class RecommendResponse(
    val count: Int,
    val `data`: List<RecommendBean>,
    val status: Int
)

data class RecommendBean(
    val animedescribe: String?,
    val animefrom: Int?,
    val animelink: String?,
    val animepicture: List<String>?,
    val animetitle: String?,
    val authorid: Int,
    val authorname: String,
    val comment: List<RecommendComment>?,
    val content: String?,
    val dramaid: Int,
    val photos: List<String>?,
    val tag: List<List<Tag>>?,
    val time: String?,
    val title: String?
)

data class RecommendComment(
    val authorid: Int,
    val authorname: String,
    val commentid: Int,
    val content: String,
    val time: String
)

data class Tag(
    val tag1: Any,
    val tag2: Any,
    val tag3: Any
)