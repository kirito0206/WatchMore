package com.example.watchmore.model.bean.userbean

data class PersonResponse(
    val `data`: UserBean,
    val status: Int,
    val message : String
)