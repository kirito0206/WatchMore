package com.example.watchmore.model.network

import com.example.watchmore.model.network.services.AnimeService
import com.example.watchmore.model.network.services.QuestionService
import com.example.watchmore.model.network.services.RecommendService
import com.example.watchmore.model.network.services.UserService
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {

    const val USER_URL = "http://47.99.197.94/user/"
    const val ANIME_URL = "http://47.99.197.94/anime/"

    val userService by lazy {
            val retrofit = Retrofit.Builder()
                .baseUrl(USER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build()
            return@lazy retrofit.create(UserService::class.java)
    }

    val animeService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(ANIME_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
        return@lazy retrofit.create(AnimeService::class.java)
    }

    val recommendService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(USER_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
        return@lazy retrofit.create(RecommendService::class.java)
    }

    val questionService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(USER_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
        return@lazy retrofit.create(QuestionService::class.java)
    }
}