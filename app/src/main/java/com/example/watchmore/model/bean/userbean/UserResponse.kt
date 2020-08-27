package com.example.watchmore.model.bean.userbean

data class UserResponse(
    val `data`: List<UserBean>,
    val status: Int,
    val message : String
)

data class UserBean(
    val Adramas: Int,
    val Rdramas: Int,
    val avatar: String,
    val collects: Int,
    val email: String,
    val fans: Int,
    val followers: Int,
    val name: String,
    val userid: Int
)