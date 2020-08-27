package com.example.watchmore.model.network.services

import com.example.watchmore.model.bean.CommonResponse
import com.example.watchmore.model.bean.animebean.AnimeBean
import com.example.watchmore.model.bean.animebean.AnimeResponse
import com.example.watchmore.model.bean.animebean.AnimeSearchResponse
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*

interface AnimeService {

    @GET("getallanime")
    suspend fun getAllAnimes() : AnimeSearchResponse

    @POST("getanime")
    suspend fun getSearchAnimes(@Field("tagid") tagid : String) : AnimeSearchResponse

    @FormUrlEncoded
    @POST("getanime")
    suspend fun getAnimeDetail(@Field("token") token: String, @Field("animeid") animeid : Int) : AnimeResponse

    @FormUrlEncoded
    @POST("like")
    suspend fun addAnimeLike(@Field("token") token : String,@Field("animeid") animeid : Int) : CommonResponse

    @DELETE("like")
    suspend fun deleteAnimeLike(@Query("token") token : String, @Query("animeid") animeid : Int) : CommonResponse

    @FormUrlEncoded
    @POST("comment")
    suspend fun addAnimeComments(@Field("token") token : String, @Field("animeid") animeid : Int, @Field("comment") comment : String) : CommonResponse

    @FormUrlEncoded
    @POST("comment") 
    suspend fun deleteAnimeComments(@Field("token") token : String,@Field("animeid") animeid : Int) : CommonResponse

    @FormUrlEncoded
    @POST("comment/star")
    suspend fun addAnimeCommentStar(@Field("token") token : String,@Field("commentid") commentid: Int) : CommonResponse

    @DELETE("comment/star")
    suspend fun deleteAnimeCommentStar(@Field("token") token : String,@Field("commentid") commentid: Int) : CommonResponse
}