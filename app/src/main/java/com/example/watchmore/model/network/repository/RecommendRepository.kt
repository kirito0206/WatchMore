package com.example.watchmore.model.network.repository

import com.example.watchmore.model.network.RetrofitHelper

class RecommendRepository{
    var recommendService = RetrofitHelper.recommendService

    suspend fun getAllRecommend() = recommendService.getAllRecommend()
}