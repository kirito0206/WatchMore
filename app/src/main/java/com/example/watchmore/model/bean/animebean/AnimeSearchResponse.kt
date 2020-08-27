package com.example.watchmore.model.bean.animebean

data class AnimeSearchResponse(
    val `data`: List<AnimeData>,
    val status: Int
)

data class AnimeData(
    val id : Int,
    val describe: String,
    val isFinish: Int,
    val link: String,
    val picture: String,
    val seasonId: String,
    val sediaId: String,
    val tag1: String?,
    val tag2: String?,
    val tag3: String?,
    val title: String
)