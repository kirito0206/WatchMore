package com.example.watchmore.model.bean.animebean

data class AnimeResponse(
    val `data`: AnimeBean,
    val status: Int
)

data class AnimeBean(
    val comments: List<Comment>,
    val describe: String,
    val id: Int,
    val isFinish: Boolean,
    val islike: Boolean,
    val likenum: Int,
    val link: String,
    val mediaId: String,
    val picture: String,
    val seasonId: String,
    val tag1: String?,
    val tag2: String?,
    val tag3: String?,
    val title: String
)

data class Comment(
    val comment: String,
    val commentid: Int,
    val starnum: Int,
    val islike: Boolean,
    val time : String,
    val username: String
)