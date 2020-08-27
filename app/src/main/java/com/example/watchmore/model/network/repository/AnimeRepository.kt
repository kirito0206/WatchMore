package com.example.watchmore.model.network.repository

import com.example.watchmore.model.network.RetrofitHelper

class AnimeRepository{

    var animeService = RetrofitHelper.animeService
    suspend fun getAllAnimes() = animeService.getAllAnimes()

    suspend fun getSearchAnimes(tagid : String) = animeService.getSearchAnimes(tagid)

    suspend fun getAnimeDetail(token:String,animeid : Int) = animeService.getAnimeDetail(token,animeid)

    suspend fun addAnimeLike(token:String,animeid : Int) = animeService.addAnimeLike(token,animeid)

    suspend fun deleteAnimeLike(token:String,animeid : Int) = animeService.deleteAnimeLike(token,animeid)

    suspend fun addAnimeComment(token: String,animeid: Int,comment : String) = animeService.addAnimeComments(token,animeid,comment)

    suspend fun deleteAnimeComment(token: String,animeid: Int) = animeService.deleteAnimeComments(token,animeid)

    suspend fun addAnimeCommentStar(token: String,commentid: Int) = animeService.addAnimeCommentStar(token,commentid)

    suspend fun deleteAnimeCommentStar(token: String,commentid: Int) = animeService.deleteAnimeCommentStar(token,commentid)
}