package com.example.watchmore.model.bean.userbean

data class FollowResponse(
    val count: Int,
    val `data`: List<Follow>,
    val status: Int
)

data class Follow(
    val avatar: String,
    val fanid: Int,
    val name: String
)