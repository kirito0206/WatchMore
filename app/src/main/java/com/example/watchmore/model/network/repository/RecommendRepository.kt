package com.example.watchmore.model.network.repository

import com.example.watchmore.model.network.RetrofitHelper
import okhttp3.MultipartBody

class RecommendRepository{
    var recommendService = RetrofitHelper.recommendService

    suspend fun getAllRecommend(page : Int) = recommendService.getAllRecommend(page)

    suspend fun getRecommendDetail(dramaid : Int) = recommendService.getRecommendDetail(dramaid)

    suspend fun createRecommend(parts : List<MultipartBody.Part>) = recommendService.createRecommend(parts)
}