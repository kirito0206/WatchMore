package com.example.watchmore.model.bean

data class CommonResponse(
    val `data`: Data,
    val status: Int,
    val message : String
)

data class Data(
    val token: String
)