package com.example.watchmore.model.bean.userbean

data class AccompanyAnimeResponse(
    val count: Int,
    val `data`: List<AccompanyAnime>,
    val status: Int
)

data class AccompanyAnime(
    val dramaid: Int,
    val photo: List<String>,
    val title: String
)